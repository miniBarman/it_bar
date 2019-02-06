package com.project.itbar.controller;

import com.project.itbar.domain.User;
import com.project.itbar.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);

        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user,
            Model model
    ) {
        user.setUsername(username);
        userRepo.save(user);
        //ToDo не отображается сообщение
        model.addAttribute("message", "Изменения были успешно сохранены");

        return "redirect:/user/"+user.getId();
    }
}
