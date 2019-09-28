package com.project.itbar.controller;

import com.project.itbar.domain.Coctail;
import com.project.itbar.repos.CoctailRepo;
import com.project.itbar.repos.JpaCoctailRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/coctail")
public class CoctailController {
    @Autowired
    private JpaCoctailRepo coctailRepo;

    @GetMapping
    public List<Coctail> list(){
        return coctailRepo.findAll();
    }

//    @GetMapping
//    public List<Map<String, String>> list() {
//        List<Map<String, String>> coctails = new ArrayList<>();
//        for (Coctail coctail : coctailRepo.findAll()){
//            coctails.add(new HashMap<String, String>() {{ put("id", coctail.getId().toString()); put("name", coctail.getName()); }});
//        }
//        return coctails;
//    }

    @GetMapping("{id}")
    public Coctail getCoctail(@PathVariable("id") Coctail coctail){
        return coctail;
    }

    @PostMapping
    public Coctail addCoctail(@RequestBody Coctail coctail){
        coctailRepo.save(coctail);
        return coctail;
    }

    @PutMapping("{id}")
    public Coctail updateCoctail (
            @PathVariable("id") Coctail coctailFromDB,
            @RequestBody Coctail coctail){
        BeanUtils.copyProperties(coctail, coctailFromDB, "id");
        return coctailRepo.save(coctailFromDB);
    }

    @DeleteMapping("{id}")
    public void deleteCoctail (@PathVariable("id") Coctail coctail){
        coctailRepo.delete(coctail);
    }
}

