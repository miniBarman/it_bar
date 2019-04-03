package com.project.itbar.controller;

import com.project.itbar.domain.User;
import com.project.itbar.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserSevice userSevice;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (!userSevice.addUser(user)) {
            model.addAttribute("message", "Такой пользователь уже существует!");
            return "registration";
        }else{
            if (user.getEmail() != null) {
                model.addAttribute("message", "Пожалуйста, пройдите по ссылке, которая была отправлена вам на почту для подтверждения");
            }
            return "login-view";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){

        boolean isActivated = userSevice.activateUser(code);

        if(isActivated){
            model.addAttribute("message", "Почта успешно подтверждена");
        }else {
            model.addAttribute("message", "Подтверждение почты не удалось. Активационный код не найден.");
        }
        return "login";
    }
}
