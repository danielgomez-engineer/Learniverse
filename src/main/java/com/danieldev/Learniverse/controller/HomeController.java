package com.danieldev.Learniverse.controller;

import com.danieldev.Learniverse.dto.TopicRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeIndex() {
        return "public/index";
    }


}
