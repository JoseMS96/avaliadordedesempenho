package br.fai.add.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //MAPEAMENTO TEMPORÁRIO

    @GetMapping("/")
    public String getLandingpage() {
        return "/home/landing-page";
    }

    @GetMapping("/employee-home")
    public String getEmployeeHomePage() {
        return "/home/employee-home";
    }

    @GetMapping("/reviewer-home")
    public String getReviewerHomePage() {
        return "/home/reviewer-home";
    }


    //MAPEAMENTO TEMPORÁRIO
}
