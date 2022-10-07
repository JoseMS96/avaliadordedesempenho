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

    @GetMapping("/2")
    public String getEmployeeHomePage() {
        return "/home/employee-home";
    }

    @GetMapping("/3")
    public String getReviewerHomePage() {
        return "/home/reviewer-home";
    }


    //MAPEAMENTO TEMPORÁRIO
}
