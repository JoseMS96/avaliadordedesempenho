package br.fai.add.client.controller;

import br.fai.add.client.service.CollaboratorService;
import br.fai.add.model.entities.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    CollaboratorService collaboratorService;

    @GetMapping("/sign-up")
    public String getSignUpPage() {
        return "account/sign-up";
    }

    @GetMapping("/sign-in")
    public String getSignInPage() {
        return "account/sign-in";
    }

    @GetMapping("/password-recovery")
    public String getPasswordRecoveryPage() {
        return "account/password-recovery";
    }

    @GetMapping("/register-employee")
    public String getEmployeeSignUpPage() {
        return "account/register-employee";
    }

    @PostMapping("/create")
    public String create(Collaborator collaborator) {

        collaboratorService.create(collaborator);
        return "redirect:/account/sign-in";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {

        Collaborator collaborator = collaboratorService.validateUsernameAndPassword(username, password);

        if (collaborator == null) {
            return "redirect:/account/sign-in";
        }

        return "redirect:/";
    }

}
