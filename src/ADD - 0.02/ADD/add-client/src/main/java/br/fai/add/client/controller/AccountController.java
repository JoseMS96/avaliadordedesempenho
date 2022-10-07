package br.fai.add.client.controller;

import br.fai.add.client.service.CollaboratorService;
import br.fai.add.client.service.OrganizationService;
import br.fai.add.client.service.impl.OrganizationServiceImpl;
import br.fai.add.model.entities.Collaborator;
import br.fai.add.model.entities.Organization;
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

    @Autowired
    OrganizationService organizationService;

    @GetMapping("/sign-up")
    public String getSignUpPage() {
        return "account/register";
    }

    @GetMapping("/sign-up-organization")
    public String getSignUpOrganizationPage() {

        return "account/register-organization";
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

    @GetMapping("/employee-form")
    public String getEmployeeForm(){return "form/employee-form";}


    @PostMapping("/create-collaborator")
    public String create(Collaborator collaborator) {

        collaboratorService.create(collaborator);
        return "redirect:/3";
    }

    @PostMapping("/create-organization")
    public String create(Organization organization){

        organizationService.create(organization);

        return "redirect:/account/sign-up";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {

        Collaborator collaborator = collaboratorService.validateUsernameAndPassword(username, password);

        if (collaborator == null) {
            return "redirect:/account/sign-in";
        }

        return "redirect:/2";
    }

}
