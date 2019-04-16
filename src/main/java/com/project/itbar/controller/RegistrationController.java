package com.project.itbar.controller;

import com.project.itbar.domain.User;
import com.project.itbar.service.UserSevice;
import com.project.itbar.utils.Constants;
import com.project.itbar.utils.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addUser(User user, Model model, RedirectAttributes redirectAttributes) {
        if (!userSevice.addUser(user)) {
            redirectAttributes.addFlashAttribute("message", new SystemMessage(Constants.MessageType.ERROR, "Такой пользователь уже существует!"));
            return "redirect:/registration";
        }else{
            if (user.getEmail() != null) {
                redirectAttributes.addFlashAttribute("message", new SystemMessage(Constants.MessageType.INFO,
                        "Пожалуйста, пройдите по ссылке, которая была отправлена вам на почту для её подтверждения"));
            }
            return "redirect:/login";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){

        boolean isActivated = userSevice.activateUser(code);

        if(isActivated){
            model.addAttribute("message", new SystemMessage(Constants.MessageType.SUCCESS, "Почта успешно подтверждена"));
        }else {
            model.addAttribute("message", new SystemMessage(Constants.MessageType.ERROR, "Подтверждение почты не удалось. Активационный код не найден"));
        }
        return "login";
    }
}
