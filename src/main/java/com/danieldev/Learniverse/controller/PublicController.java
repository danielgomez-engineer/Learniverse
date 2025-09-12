package com.danieldev.Learniverse.controller;

import com.danieldev.Learniverse.dto.response.ContentResponse;
import com.danieldev.Learniverse.dto.response.SectionResponse;
import com.danieldev.Learniverse.dto.response.SubtopicResponse;
import com.danieldev.Learniverse.dto.response.TopicResponse;
import com.danieldev.Learniverse.service.ContentService;
import com.danieldev.Learniverse.service.SectionService;
import com.danieldev.Learniverse.service.SubtopicService;
import com.danieldev.Learniverse.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PublicController {

    private final TopicService topicService;
    private final SubtopicService subtopicService;
    private final ContentService contentService;
    private final SectionService sectionService;

    @GetMapping("topic_details") // detalles de temas
    public String showTopic_details(Model model) { // me lleva a la lista de temas pulic/topic_details
        List<TopicResponse> topics = topicService.findAllTopics();
        model.addAttribute("topics", topics);
        return "public/topic/topic_details";
    }

    @GetMapping("subtopic_details") // detalles de subtemas
    public String showSubtopic_details(Model model) { // me lleva a la lista de temas pulic/subtopic_details
        List<SubtopicResponse> subtopics = subtopicService.findAllSubtopics();
        model.addAttribute("subtopics", subtopics);
        return "public/subtopic/subtopic_details";
    }

    //detalles de subtemas pertenecientes a un tema
    @GetMapping("/topics/{topicId}/subtopics")
    public String viewSubtopicsByTopic(@PathVariable Long topicId, Model model) {
        TopicResponse topic = topicService.findTopicById(topicId);
        List<SubtopicResponse> subtopics = subtopicService.findByTopicId(topicId);

        model.addAttribute("topic", topic);
        model.addAttribute("subtopics", subtopics);

        return "public/subtopic/subtopic_details";
    }

    // MOSTRAR CONTENIDOS DE UN SUBTEMA

    @GetMapping("/subtopics/{subtopicId}/contents")
    public String viewContentsBySubtopic(@PathVariable Long subtopicId, Model model) {
        SubtopicResponse subtopic = subtopicService.findByIdSubtopic(subtopicId);
        List<ContentResponse> contents = contentService.findBySubtopicId(subtopicId);

        model.addAttribute("subtopic", subtopic);
        model.addAttribute("contents", contents);

        return "public/content/content_details";
    }


    // DETALLES DEL CONTENIDO CON SUS SECCIONES
    @GetMapping("content_details")
    public String viewContentDetails(Model model) {
        List<ContentResponse> contents = contentService.findAll();

        model.addAttribute("contents", contents);
        return "public/content/content_details";
    }

    //MOSTRAR SECCIONES COMPLETAS DE UN CONTENIDO
    @GetMapping("/contents/{idContent}/sections")
    public String viewSectionsByContent(@PathVariable Long idContent, Model model) {
        ContentResponse content = contentService.findById(idContent);
        List<SectionResponse> sections = sectionService.findByContentId(idContent);

        model.addAttribute("content", content);
        model.addAttribute("sections", sections);
        return "public/section/sections_by_content";
    }


    // MOSTRAR SECCIÓN EN PÁGINA COMPLETA
    @GetMapping("/sections/{idSection}")
    public String viewContentSections(@PathVariable Long idSection, Model model) {

        SectionResponse section = sectionService.findById(idSection);
        SectionResponse previous = sectionService.findPrevious(idSection);
        SectionResponse next = sectionService.findNext(idSection);

        model.addAttribute("section", section);
        model.addAttribute("previous", previous);
        model.addAttribute("next", next);

        return "public/section/section_details"; // Muestra la sección completa
    }

    //footer
    @GetMapping("/terminos")
    public String terminos() {
        return "public/terminos";
    }

    @GetMapping("/privacidad")
    public String privacidad() {
        return "public/privacidad";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "public/contacto";
    }


}
