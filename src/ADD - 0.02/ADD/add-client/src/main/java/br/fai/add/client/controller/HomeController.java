package br.fai.add.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //MAPEAMENTO TEMPORÁRIO
    @GetMapping("/")
    public String getReviewerHomePage() {
        return "/home/reviewer-home";
    }


    @GetMapping("/2")
    public String getEmployeeHomePage() {
        return "/home/employee-home";
    }

    //MAPEAMENTO TEMPORÁRIO
}
