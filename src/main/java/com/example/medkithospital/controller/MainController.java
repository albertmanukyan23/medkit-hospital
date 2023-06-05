package com.example.medkithospital.controller;

import com.example.medkithospital.entity.UserType;
import com.example.medkithospital.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("customLogin")
    public String customLogin() {
        return "customLogin";

    }

    @GetMapping("/customSuccessLogIn")
    public String customSuccessLogIn(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            if (currentUser.getUser().getUserType() == UserType.PATIENT) {
                return "redirect:/";
            } else {
                return "redirect:/user/register";
            }
        }
        return "/customLogin";

    }

}
