package com.danieldev.Learniverse.controller.admin;

import com.danieldev.Learniverse.dto.request.UserRequest;
import com.danieldev.Learniverse.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String viewFormRegister(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserRequest request, BindingResult result) {

        if(result.hasErrors()) {
            return "auth/register";
        }
        userService.create(request);
        return "redirect:/auth/login?success";
    }

    @GetMapping("/login")
    public String viewFormLogin() {
        return "auth/login";
    }

    @GetMapping("/dashboard")
    public String viewDashboard(Model model) {

        //model.addAttribute("user", )
        return "public/dashboard";
    }
}
