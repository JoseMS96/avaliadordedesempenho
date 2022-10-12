package br.fai.add.client.controller;

import br.fai.add.client.service.CollaboratorService;
import br.fai.add.client.service.OrganizationService;
import br.fai.add.model.entities.Collaborator;
import br.fai.add.model.entities.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    CollaboratorService collaboratorService;

    @Autowired
    OrganizationService organizationService;


    @GetMapping("/sign-up-organization")
    public String getSignUpOrganizationPage() {
        return "account/register-organization";
    }

    @GetMapping("/sign-in")
    public String getSignInPage() {
        return "account/sign-in-page";
    }

    @GetMapping("/password-recovery")
    public String getPasswordRecoveryPage() {
        return "account/password-recovery";
    }

    @GetMapping("/register-employee")
    public String getEmployeeSignUpPage() {
        return "account/register-employee";
    }

    @GetMapping("/sign-up")
    public String getOrganizations(final Model model) {

        List<Organization> organizations = organizationService.find();

        if (organizations == null || organizations.isEmpty()) {
            model.addAttribute("organizations", new ArrayList<Organization>());
        } else {

            model.addAttribute("organizations", organizations);
        }


        return "account/register"; //para a pagina dos dropdowns DOS REVIEWERS
    }


    @PostMapping("/create-collaborator")
    public String create(Collaborator collaborator, @RequestParam("orgId") final int orgId) {

        Organization organization = (Organization) organizationService.findById(orgId);

        collaborator.setOrganization(organization);

        collaboratorService.create(collaborator);


        return "redirect:/account/sign-in";
    }


    @PostMapping("/create-organization")
    public String create(Organization organization) {



        organizationService.create(organization);

        return "redirect:/account/sign-up";
    }

    @GetMapping("/profile")
    public String getProfilePage(final Model model, final HttpSession session) {

        Collaborator collaborator = (Collaborator) session.getAttribute("currentUser");
//        UserModel user = (UserModel) userService.findById(id);

        if (collaborator == null) {
            // futuramente iremos injetar o erro
            return "refirect:/user/list";
        }

        model.addAttribute("collaborator", collaborator); //testar verde

        return "collaborator/detail";
    }
}
//    @PostMapping("/login")
//    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
//
//        Collaborator collaborator = collaboratorService.validateUsernameAndPassword(username, password);
//
//        if (collaborator == null) {
//            return "redirect:/account/sign-in";
//        }
//
//        return "redirect:/2";



