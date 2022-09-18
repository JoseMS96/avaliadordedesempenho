package br.fai.add.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/form")
public class FormController {


    @GetMapping("/answer-form")
    public String getAnswerFormPage() {
        return "form/answer-form";
    }

    @GetMapping("/create-form")
    public String getCreateFormPage() {
        return "form/create-form";
    }

    @GetMapping("/pending-form")
    public String getPendingFormPage() {
        return "form/pending-form";
    }

    @GetMapping("/view-form")
    public String getViewFormPage() {
        return "form/view-form";
    }

    //para testes
}
