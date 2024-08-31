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
    public String admin(Model model){
        return "adm.html";
    }

    @GetMapping("/user")
    public String userenc(Model model){
        return "usersenc.html";
    }

    @GetMapping("/admcapitulos")
    public String capitulos(Model model){
        return "admcapitulos.html";
    }

    @GetMapping("/admpreguntas")
    public String preguntas(Model model){
        return "admpreguntas.html";
    }

    @GetMapping("/admencuestas")
    public String encuesta(Model model){
        return "admencuestas.html";
    }

    @GetMapping("/admusuarios")
    public String usuarios(Model model){
        return "admusuarios.html";
    }

    
    


}
