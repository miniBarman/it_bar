package com.project.itbar.controller;

import com.project.itbar.domain.Coctail;
import com.project.itbar.domain.User;
import com.project.itbar.repos.CoctailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private CoctailRepo coctailRepo;

    @Value("${upload.path}")
    private String uploadPath;//ToDo probably need to move this in some one place in Application or somthing like that

    //just redirection from / to /main
    //maybe we can use something more smooth???
    @GetMapping("/")
    public String main(Model model) {
        return "redirect:/main";
    }

    //same problem WA for login button
    @GetMapping("/main_login")
    public String mainLogin(Model model) {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Coctail> coctails;

        if (filter != null && !filter.isEmpty()) {
            coctails = coctailRepo.findByName(filter);
        } else {
            coctails = coctailRepo.findAll();
        }

        model.addAttribute("coctails", coctails);
        model.addAttribute("filter", filter);

        return "main";
    }

    //don't know but page doesn't work without this stuff
    //probably there should be at least 1 get method on page
    @GetMapping("/add")
    public String add(Model model){
        return "add";
    }

    @PostMapping("/add")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String name,
                      @RequestParam String description,
                      Map<String, Object> model,
                      @RequestParam("file") MultipartFile file) throws IOException {

        Coctail coctail = new Coctail(name, description, user);

        if(file != null){
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/img/" + resultFilename));

            coctail.setImage(resultFilename);
        }
        coctailRepo.save(coctail);
        model.put("message", "Коктейль " + name + " был успешно добавлен");

        return "add";
    }
}