package br.fai.add.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {

    @RequestMapping("/default-home")
    public String defaultLoginUrl(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_REVIEWER")) {
            return "redirect:/reviewer-home/";
        }
        return "redirect:/employee-home/";
    }
}

