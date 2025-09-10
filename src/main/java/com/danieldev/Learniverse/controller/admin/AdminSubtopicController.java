package com.danieldev.Learniverse.controller.admin;


import com.danieldev.Learniverse.dto.request.SubtopicRequest;
import com.danieldev.Learniverse.dto.response.SubtopicResponse;
import com.danieldev.Learniverse.dto.response.TopicResponse;
import com.danieldev.Learniverse.service.SubtopicService;
import com.danieldev.Learniverse.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class AdminSubtopicController {

    private final TopicService topicService;
    private final SubtopicService subtopicService;


    //me lleva al admin_subtopics donde estan todos los subtemas pertenecientes a un tema
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin_subtopics")
    public String viewAdminSubtopics(Model model) {
        List<SubtopicResponse> subtopics = subtopicService.findAllSubtopics();

        model.addAttribute("subtopics", subtopics);

        return "admin/subtopics/admin_subtopics";

    }
        //*************ME LLEVA AL FORM DE CREAR SUBTEMA************* */
    @GetMapping("/subtopic/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String ShowFormCreateSubtopic(Model model) {
        model.addAttribute("topics", topicService.findAllTopics());
        model.addAttribute("subtopic", new SubtopicRequest());
        return "admin/subtopics/create_subtopic";
    }

    /*AQUI GUARDAMOS EL SUBTEMA EN LA BASE DE DATOS*/
    @PostMapping("/subtopics")
    @PreAuthorize("hasRole('ADMIN')")
    public String savedSubtopic(@Valid @ModelAttribute("subtopic") SubtopicRequest request,
                                BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("erros", result.getAllErrors());
            return "admin/subtopics/create_subtopic";
        }
        subtopicService.createSubtopic(request);
        return "redirect:/admin_subtopics";
    }


    //********************************MOSTRAR FORMULARIO PARA EDITAR SUBTEMA*/
    @GetMapping("/subtopics/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String viewFormEdit(@PathVariable Long id, Model model) {

        //el subtema que se va a actualizar
        SubtopicResponse response = subtopicService.findByIdSubtopic(id);


        SubtopicRequest request = new SubtopicRequest();
        request.setId(response.getId());
        request.setTitle(response.getTitle());
        request.setDescription(response.getDescription());
        request.setIdTopic(response.getIdTopic());

        model.addAttribute("subtopic", request);
        model.addAttribute("topics", topicService.findAllTopics());

        return "admin/subtopics/edit_subtopic";
    }


    //********************************GUARDAR SUBTEMA*/
    @PostMapping("/subtopics/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateSubtopic(@PathVariable Long id,
                                 @Valid @ModelAttribute("subtopic") SubtopicRequest request,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            // Necesario para que el select de temas siga cargado
            model.addAttribute("topics", topicService.findAllTopics());
            return "admin/subtopics/edit_subtopic";
        }

        subtopicService.updateSubtopic(request, id);
        return "redirect:/admin_subtopics";
    }

    //********************************NOS LLEVA AL FORMULARIO PARA CONFIRMAR AL ELIMINACION*/
    @GetMapping("/subtopics/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String viewDeleteSubtopic(Model model, @PathVariable  Long id) {
        SubtopicResponse response = subtopicService.findByIdSubtopic(id);
        if (response == null) {
            // Si no existe el subtema, redirigir con error
            model.addAttribute("message", "El Subtema no existe o ya fue eliminado.");
            return "error/404";
        }
        model.addAttribute("subtopic", response);
        return "admin/subtopics/delete_subtopic"; // Nombre de tu HTML de confirmación
    }

    @PostMapping("/subtopics/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTopic(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        subtopicService.deleteSubtopic(id);
        redirectAttributes.addFlashAttribute("successMessage", "Subtema eliminado correctamente");
        return "redirect:/admin_subtopics";
    }

}
