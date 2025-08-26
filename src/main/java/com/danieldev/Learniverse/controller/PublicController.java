package com.danieldev.Learniverse.controller;

import com.danieldev.Learniverse.dto.response.ContentResponse;
import com.danieldev.Learniverse.dto.response.SubtopicResponse;
import com.danieldev.Learniverse.dto.response.TopicResponse;
import com.danieldev.Learniverse.service.ContentService;
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

    @GetMapping("topic_details") // detalles de temas
    public String showTopic_details(Model model) { // me lleva a la lista de temas pulic/topic_details
        List<TopicResponse> topics = topicService.findAllTopics();
        model.addAttribute("topics", topics);
        return "public/topic/topic_details";
    }

    @GetMapping("subtopic_details") // detalles de temas
    public String showSubtopic_details(Model model) { // me lleva a la lista de temas pulic/topic_details
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

    ///  DETALLES DE CONTENIDOS
    @GetMapping("content_details")
    public String viewContentDetails(Model model) {
        List<ContentResponse> contents = contentService.findAll();
        model.addAttribute("contents", contents);
        return "public/content/content_details";
    }

    /// MOSTRAR CONTENIDOS DE UN SUBTEMA

    @GetMapping("/subtopics/{subtopicId}/contents")
    public String viewContentsBySubtopic(@PathVariable Long subtopicId, Model model) {
        SubtopicResponse subtopic = subtopicService.findByIdSubtopic(subtopicId);
        List<ContentResponse> contents = contentService.findBySubtopicId(subtopicId);

        model.addAttribute("subtopic", subtopic);
        model.addAttribute("contents", contents);

        return "public/content/content_details";
    }







}
