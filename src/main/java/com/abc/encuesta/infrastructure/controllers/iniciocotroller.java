package com.abc.encuesta.infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class iniciocotroller {

    @GetMapping("/home")
    public String home(Model model){
        return "index.html";
    }

    @GetMapping("/admin")
    public String adminenc(Model model){
        return "adminenc.html";
    }

    @GetMapping("/user")
    public String userenc(Model model){
        return "usersenc.html";
    }

    


}
