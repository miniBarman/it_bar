package com.project.itbar.controller;

import com.project.itbar.domain.User;
import com.project.itbar.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userEmail", "test");
        return "profile";
    }

    @PostMapping("/profile")
    public String editProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userEmail", user.getEmail());
        return "profile";
    }
}

