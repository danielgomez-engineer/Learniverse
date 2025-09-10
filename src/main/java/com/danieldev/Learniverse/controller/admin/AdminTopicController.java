package com.danieldev.Learniverse.controller.admin;

import com.danieldev.Learniverse.dto.request.TopicRequest;
import com.danieldev.Learniverse.dto.response.TopicResponse;
import com.danieldev.Learniverse.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminTopicController {

    private final TopicService topicService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/manage") // mostrar manage donde se gestionan los recursos
    public String showManageResource(Authentication a) {
        System.out.println("Usuario autemticado " + a.getName());
        a.getAuthorities().forEach(aa ->
                System.out.println(">>> Authority en controller: " + aa.getAuthority())
        );
        return "admin/manage";
    }

    //administrador de temas
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin_topics")
    public String viewAdminTopics(Model model) {
        List<TopicResponse> topics = topicService.findAllTopics();
        model.addAttribute("topics", topics);
        return "admin/topics/admin_topics";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/topics/new")// de manage nos muestra el form de crear tema
    public String showFormCreate(Model model) {
        model.addAttribute("topic", new TopicRequest());
        return "admin/topics/create";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/topics") //guardar el tema
    public String saveTopic(@Valid @ModelAttribute("topic") TopicRequest request,
                            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/topics/create";
        }
        topicService.createTopic(request);
        return "redirect:/admin_topics";
    }

    //*******FORMULARIO PARA MOSTRAR EDITAR TEMA********************
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/topics/edit/{id}")
    public String showFormEdit(Model model, @PathVariable Long id) {

        TopicResponse response = topicService.findTopicById(id);

        TopicRequest request = new TopicRequest();
        request.setId(response.getId());
        request.setTitle(response.getTitle());
        request.setDescription(response.getDescription());

        model.addAttribute("topic", request);
        return "admin/topics/edit";

    }

    //*******FORMULARIO PARA GUARDAR TEMA EN LA BASE DE DATOS********************
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/topics/edit/{id}")
    public String updateTopic(@PathVariable Long id,
                               @Valid @ModelAttribute("topic") TopicRequest request,
                               BindingResult result) {

        if (result.hasErrors()) {
            return "admin/topics/edit";
        }
        request.setId(id);
        topicService.updateTopic(request, id);
        return "redirect:/admin_topics";
    }

    @GetMapping("/topics/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showDeleteForm(Model model, @PathVariable Long id) {
        TopicResponse response = topicService.findTopicById(id);
        if (response == null) {
            // Si no existe el tema, redirigir con error
            model.addAttribute("message", "El tema no existe o ya fue eliminado.");
            return "error/404"; // O tu vista de error personalizada
        }
        model.addAttribute("topic", response);
        return "admin/topics/delete-confirm"; // Nombre de tu HTML de confirmación
    }

    @PostMapping("/topics/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTopic(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        topicService.deleteTopic(id);
        redirectAttributes.addFlashAttribute("successMessage", "Tema eliminado correctamente");
        return "redirect:/admin_topics";
    }

}
