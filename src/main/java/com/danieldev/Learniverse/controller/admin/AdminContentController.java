package com.danieldev.Learniverse.controller.admin;

import com.danieldev.Learniverse.dto.request.ContentRequest;
import com.danieldev.Learniverse.dto.response.ContentResponse;
import com.danieldev.Learniverse.service.ContentService;
import com.danieldev.Learniverse.service.SubtopicService;
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
public class AdminContentController {

    private final ContentService contentService;
    private final SubtopicService subtopicService;

    //ME LLEVA AL ADMIN DE CONTENIDOS
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin_contents")
    public String viewAdminContents(Model model) {
        List<ContentResponse> contents = contentService.findAll();
        model.addAttribute("contents", contents);
        return "admin/contents/admin_contents";
    }

    // ME LLEVA AL FORM DE CREAR CONTENIDO CON UN REQUEST QUE ES EL QUE CREAREMOS Y UNA LISTA DE SUBTEMAS PARA QUE
    @GetMapping("/contents/new")///SELECCIONE A QUE SUBTEMA VA A PERTENER
    @PreAuthorize("hasRole('ADMIN')")
    public String viewFormCreate(Model model) {
        model.addAttribute("subtopics", subtopicService.findAllSubtopics());
        model.addAttribute("content", new ContentRequest());
        return "admin/contents/create_content";
    }

    // GUARDAMOS EL CONTENIDO QUE RECIBIMOS DEL FORM
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/contents")
    public String savebContent(@Valid @ModelAttribute("content") ContentRequest request,
                               BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/contents/create_content";
        }
        contentService.create(request);
        return "redirect:/admin_contents";
    }

    // MOSTRAR FORMULARTIO DE EDITAR
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/contents/edit/{id}")
    public String viewFormEdit(@PathVariable Long id, Model model) {

        ContentResponse response = contentService.findById(id);

        ContentRequest request = new ContentRequest();
        request.setId(response.getId());
        request.setTitle(response.getTitle());
        request.setDescription(response.getDescription());
        request.setIdSubtopic(response.getIdSubtopic());

        model.addAttribute("content", request);
        model.addAttribute("subtopics", subtopicService.findAllSubtopics());

        return "admin/contents/edit_content";
    }

    // GUARDAMOS SUBTEMA
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/contents/edit/{id}")
    public String updateContent(@PathVariable Long id,
                                @Valid @ModelAttribute("content") ContentRequest request,
                                BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("subtopics", subtopicService.findAllSubtopics());
            return "admin/contents/edit_content";
        }
        contentService.update(id, request);
        return "redirect:/admin_contents";
    }

    // FORMULARIO PARA CONFIRMAR LA ELIMINACION
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/contents/delete/{id}")
    public String viewDeleteContent (Model model, @PathVariable Long id) {
        ContentResponse response = contentService.findById(id);
        if(response == null) {
            model.addAttribute("message", "No existe contenido o ya fue eliminado");
            return "error/404";
        }
        model.addAttribute("content", response);
        return "admin/contents/delete_content";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("contents/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        contentService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Contenido Eliminado correctamente");
        return "redirect:/admin_contents";
    }
}
