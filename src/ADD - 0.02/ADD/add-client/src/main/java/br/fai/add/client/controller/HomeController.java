package br.fai.add.client.controller;

import br.fai.add.client.service.FormService;
import br.fai.add.client.service.RespondentService;
import br.fai.add.model.entities.Collaborator;
import br.fai.add.model.entities.Form;
import br.fai.add.model.entities.Respondent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FormService formService;

    @Autowired
    private RespondentService respondentService;

    @GetMapping("/")
    public String getLandingpage() {
        return "/home/landing-page";
    }

    @GetMapping("/employee-home")
    public String getEmployeeHomePage(final Model model, HttpSession session) {

        Collaborator collaborator_employee = (Collaborator) session.getAttribute("currentUser");

        List<Form> forms = formService.findUnansweredForms(collaborator_employee.getId());

        if (forms == null || forms.isEmpty()) {
            model.addAttribute("forms", new ArrayList<Form>());
        } else {

            model.addAttribute("forms", forms);
        }
        model.addAttribute("currentUser", collaborator_employee);
        return "/home/employee-home";
    }

    @GetMapping("/reviewer-home")
    public String getReviewerHomePage(final Model model, HttpSession session) {

        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");

        List<Respondent> respondents = respondentService.findRespondentsByOrg(collaborator_reviewer.getOrganization().getId());

        if (respondents == null || respondents.isEmpty()) {
            model.addAttribute("respondents", new ArrayList<Respondent>());
        } else {

            model.addAttribute("respondents", respondents);
        }


        model.addAttribute("currentUser", collaborator_reviewer);
        return "/home/reviewer-home";
    }


    //MAPEAMENTO TEMPORÁRIO
}
