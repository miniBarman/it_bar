package com.project.itbar.controller;

import com.project.itbar.domain.Coctail;
import com.project.itbar.repos.CoctailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private CoctailRepo coctailRepo;

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Coctail> coctails = coctailRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            coctails = coctailRepo.findByName(filter);
        } else {
            coctails = coctailRepo.findAll();
        }

        model.addAttribute("coctails", coctails);
        model.addAttribute("filter", filter);

        return "main";
    }

//    @GetMapping("/add")
//    public String add(Model model){
//        return "add";
//    }

    @GetMapping("/add")
    public String add(Model model){
        return "add";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String description, Map<String, Object> model) {
        Coctail coctail = new Coctail(name, description);
        coctailRepo.save(coctail);
        model.put("message", "Коктейль " + name + " был успешно добавлен");

//        Iterable<Coctail> coctails = coctailRepo.findAll();
//        model.put("coctails", coctails);
        return "add";
    }
}