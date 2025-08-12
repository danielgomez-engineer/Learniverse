package com.danieldev.Learniverse.controller.admin;

import com.danieldev.Learniverse.dto.TopicRequest;
import com.danieldev.Learniverse.dto.TopicResponse;
import com.danieldev.Learniverse.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AdminTopicController {

    private final TopicService topicService;

    @GetMapping("/manage") // mostrar manage donde se gestionan los eventos
    public String showManageResource(Model model) {

        model.addAttribute("topics", topicService.findAllTopics());
        return "admin/manage";
    }


    @GetMapping("/topic/new")// de manage nos muestra el form de crear tema
    public String showFormCreate(Model model) {
        model.addAttribute("topic", new TopicRequest());
        return "admin/topic/create";
    }

    @PostMapping("/topics") //guardar el tema
    public String saveTopic(@Valid @ModelAttribute("topic") TopicRequest request,
                            BindingResult result, Model model) {


        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/topic/create";
        }
        topicService.createTopic(request);
        return "redirect:/manage";
    }

    //formulario para  mostrar editar tema
    @GetMapping("/topic/edit/{id}")
    public String showFormEdit(Model model, @PathVariable Long id) {

        TopicResponse response = topicService.findTopicById(id);

        TopicRequest request = new TopicRequest();
        request.setId(response.getId());
        request.setTitle(response.getTitle());
        request.setDescription(response.getDescription());

        model.addAttribute("topic", request);
        return "admin/topic/edit";

    }

    //guardar tema en la base de datos

    @PostMapping("/topic/edit/{id}")
    public String uopdateTopic(@PathVariable Long id,
                               @Valid @ModelAttribute("topic") TopicRequest request,
                               BindingResult result) {

        if (result.hasErrors()) {
            return "admin/topic/edit";
        }
        request.setId(id);
        topicService.updateTopic(request, id);
        return "redirect:/manage";
    }

    @GetMapping("/topics/delete/{id}")
    public String showDeleteForm(Model model, @PathVariable Long id) {
        TopicResponse response = topicService.findTopicById(id);
        if (response == null) {
            // Si no existe el tema, redirigir con error
            model.addAttribute("message", "El tema no existe o ya fue eliminado.");
            return "error"; // O tu vista de error personalizada
        }
        model.addAttribute("topic", response);
        return "admin/topic/delete-confirm"; // Nombre de tu HTML de confirmación
    }

    @PostMapping("/topics/delete/{id}")
    public String deleteTopic(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        topicService.deleteTopic(id);
        redirectAttributes.addFlashAttribute("successMessage", "Tema eliminado correctamente");
        return "redirect:/manage";
    }

}
