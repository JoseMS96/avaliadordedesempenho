package br.fai.add.client.controller;

import br.fai.add.client.service.*;
import br.fai.add.model.entities.Collaborator;
import br.fai.add.model.entities.Form;
import br.fai.add.model.entities.Question;
import br.fai.add.model.entities.Respondent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/form")
public class FormController {


    @Autowired
    private FormService formService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RespondentService respondentService;

    @GetMapping("/create-form-page")
    public String getFormCreatePage(final Model model, HttpSession session) {

        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");

        List<Form> forms = formService.findAllForms(collaborator_reviewer.getId());

        if (forms == null || forms.isEmpty()) {
            model.addAttribute("forms", new ArrayList<Form>());
        } else {

            model.addAttribute("forms", forms);
        }

        return "form/create-form";
    }

    @PostMapping("/create-form")
    public String Create(Form form, HttpSession session) {

        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");

        Collaborator collaborator = (Collaborator) collaboratorService.findById(collaborator_reviewer.getId());

        form.setCollaborator(collaborator);

        formService.create(form);

        return "redirect:/form/create-form-page";
    }

    @GetMapping("/form-details/{id}")
    public String getFormDetailPage(@PathVariable("id") final int id, final Model model) {


        Form form = (Form) formService.findById(id);

        model.addAttribute("form", form);


        List<Question> questions = questionService.findQuestionsByForm(id);

        if (questions == null || questions.isEmpty()) {
            model.addAttribute("questions", new ArrayList<Question>());
        } else {
            model.addAttribute("questions", questions);
        }

        List<Respondent> respondents = respondentService.find();

        if (respondents == null || respondents.isEmpty()) {
            model.addAttribute("respondents", new ArrayList<Respondent>());
        } else {
            model.addAttribute("respondents", respondents);
        }

        return "form/details-form"; //
    }

    @GetMapping("/add-employee/{id}")
    public String getAddEmployeePage(@PathVariable("id") final int id, HttpSession session, final Model model) {

        Form form = (Form) formService.findById(id);

        model.addAttribute("form", form);

        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");

        List<Collaborator> collaborators = collaboratorService.findCollaboratorsByOrganization(collaborator_reviewer.getOrganization().getId());

        if (collaborators == null || collaborators.isEmpty()) {
            model.addAttribute("collaborators", new ArrayList<Collaborator>());
        } else {

            model.addAttribute("collaborators", collaborators);
        }
        return "form/employee-form";
    }

    @PostMapping("/add-employee")
    public String create(Respondent respondent, @RequestParam("colId") final int colId, @RequestParam("formId") final int formId) {

        Collaborator collaborator = (Collaborator) collaboratorService.findById(colId);
        respondent.setCollaborator(collaborator);

        Form form = (Form) formService.findById(formId);
        respondent.setForm(form);

        respondent.setAnswered(false);

        respondentService.create(respondent);

        return "redirect:/form/form-details/" + formId;
    }

//criar create do employee


    @GetMapping("/add-question")
    public String getAddQuestionPage() {


        return "form/question-form";
    }


    @GetMapping("/answer-form")
    public String getAnswerFormPage() {
        return "form/answer-form";
    }

    @GetMapping("/question-form")
    public String getQuestionPage() {
        return "form/question-form";
    }

    @GetMapping("/employee-form")
    public String getPageCollaborator() {
        return "form/employee-form";
    }

    //chamando a pagina conseguir vizualizar pra cria-la
    @GetMapping("/view-form")
    public String getPageViewForm() {
        return "form/view-form";
    }

}
