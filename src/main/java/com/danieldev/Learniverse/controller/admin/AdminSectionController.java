package com.danieldev.Learniverse.controller.admin;

import com.danieldev.Learniverse.dto.request.ContentRequest;
import com.danieldev.Learniverse.dto.request.SectionRequest;
import com.danieldev.Learniverse.dto.response.SectionResponse;
import com.danieldev.Learniverse.service.ContentService;
import com.danieldev.Learniverse.service.SectionService;
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
public class AdminSectionController {

    private final SectionService sectionService;
    private final ContentService contentService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin_sections")
    public String viewAdminSection(Model model) {
        List<SectionResponse> sections = sectionService.findAll();
        model.addAttribute("sections", sections);
        return "admin/sections/admin_sections";
    }

    //METODO PARA CREAR UNA SECCION Y MUESTRA UNA LISTA DE CONTENIDOS QUE ES DONDE VA A seleccionar a que contenido
    @GetMapping("/section/new")//pertenece, igual con los lenguajes
    @PreAuthorize("hasRole('ADMIN')")
    public String viewFormCreate(Model model) {
        model.addAttribute("contents", contentService.findAll());
        model.addAttribute("section", new SectionRequest());
        model.addAttribute("languages", com.danieldev.Learniverse.model.Language.values());
        return "admin/sections/create_section";
    }

    //METODO PARA GUARDAR LA SECCION QUE VIENE DEL FORM
    @PostMapping("/sections")
    @PreAuthorize("hasRole('ADMIN')")
    public String savedSection(@Valid @ModelAttribute("section")SectionRequest request,
                               BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/sections/create_section";
        }
        sectionService.create(request);
        return "redirect:/admin_sections";
    }

    // NOS LLEVA AL FORM DE EDTIAR
    @GetMapping("/sections/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String viewFormEdit(@PathVariable Long id, Model model) {
        SectionResponse response = sectionService.findById(id);

        SectionRequest request = new SectionRequest();
        request.setId(response.getId());
        request.setTitle(response.getTitle());
        request.setDescription(response.getDescription());
        request.setLanguage(response.getLanguage());
        request.setCode(response.getCode());
        request.setUrlVideo(response.getUrlVideo());
        request.setIdContent(response.getIdContent());

        model.addAttribute("section", request);
        model.addAttribute("contents", contentService.findAll());
        model.addAttribute("languages", com.danieldev.Learniverse.model.Language.values());
        return "admin/sections/edit_section";
    }

    //GUARDAMOS LA SECCION ACTUALIZADA
    @PostMapping("/sections/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateSection(@PathVariable Long id,
                                @Valid @ModelAttribute("section") SectionRequest request,
                                BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("sections", sectionService.findAll());
            return "admin/sections/edit_section";
        }
        sectionService.update(id, request);
        return "redirect:/admin_sections";
    }



    @GetMapping("/sections/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSection(@PathVariable Long id, Model model) {
        SectionResponse response = sectionService.findById(id);

        if(response == null) {
            model.addAttribute("message", "No existe esta seccion o ya fue eliminado. controller/admin/adminSectionController.");
            return "error/404";
        }
        model.addAttribute("section", response);
        return "admin/sections/delete_section";
    }

    @PostMapping("/sections/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSection(@PathVariable Long id, RedirectAttributes redirectAttributes){
        sectionService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Seccion eliminada correctamente");
        return "redirect:/admin_sections";
    }

}
