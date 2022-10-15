package br.fai.add.client.controller;

import br.fai.add.model.entities.Collaborator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/not-found")
    public String getNotFoundPage(HttpSession session, final Model model) {
        Collaborator collaborator_employee = (Collaborator) session.getAttribute("currentUser");
        model.addAttribute("currentUser", collaborator_employee);
        return "common/not-found";
    }

    @GetMapping("/acess-denied")
    public String getAcessDeniedPage(HttpSession session, final Model model) {
        Collaborator collaborator_employee = (Collaborator) session.getAttribute("currentUser");
        model.addAttribute("currentUser", collaborator_employee);
        return "common/acess-denied";
    }

}
