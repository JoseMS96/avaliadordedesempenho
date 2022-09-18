package br.fai.add.client.controller;

import br.fai.add.client.service.UserService;
import br.fai.add.model.entities.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/collaborator")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    public String getUsers(final Model model) {

        List<Collaborator> collaborators = userService.find();

        if (collaborators == null || collaborators.isEmpty()) {
            model.addAttribute("collaborators", new ArrayList<Collaborator>());
        } else {
            model.addAttribute("collaborators", collaborators);
        }

        return "user/list-collaborators";
    }


}


