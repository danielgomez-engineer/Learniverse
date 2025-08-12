package com.danieldev.Learniverse.controller;

import com.danieldev.Learniverse.dto.TopicResponse;
import com.danieldev.Learniverse.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PublicController {

    private final TopicService topicService;

    @GetMapping("topic_details")
    public String showTopic_details(Model model) { // me lleva a la lista de temas pulic/topic_details
        List<TopicResponse> topics = topicService.findAllTopics();
        model.addAttribute("topics", topics);
        return "public/topic/topic_details";
    }



}
