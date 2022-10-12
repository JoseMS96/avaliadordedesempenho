package br.fai.add.client.controller;

import br.fai.add.client.service.AnswerService;
import br.fai.add.client.service.CollaboratorService;
import br.fai.add.client.service.FormService;
import br.fai.add.client.service.QuestionService;
import br.fai.add.model.entities.Collaborator;
import br.fai.add.model.entities.Form;
import br.fai.add.model.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    //só quero listar formularios criado pelo reviewer atual e não realizados

    @GetMapping("/create-form-page")
    public String getFormCreatePage(final Model model) {

        List<Form> forms = formService.findUnansweredForms();

        if (forms == null || forms.isEmpty()) {
            model.addAttribute("forms", new ArrayList<Form>());
        } else {

            model.addAttribute("forms", forms);
        }

        return "form/create-form-page";
    }

    @PostMapping("/create-form")
    public String Create(Form form, HttpSession session) {

        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");

        Collaborator collaborator = (Collaborator) collaboratorService.findById(collaborator_reviewer.getId());

        form.setCollaborator(collaborator);

        formService.create(form);

        return "redirect:/form/create-form-page";
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

    @GetMapping("/form-details")
    public String getFormDetailPage(final Model model) {
        //finbyId LIST AQUI id do form
        List<Question> questions = questionService.find();

        if (questions == null || questions.isEmpty()) {
            model.addAttribute("questions", new ArrayList<Question>());
        } else {
            model.addAttribute("questions", questions);
        }


        List<Collaborator> collaborators = collaboratorService.find();

        if (collaborators == null || collaborators.isEmpty()) {
            model.addAttribute("collaborators", new ArrayList<Collaborator>());
        } else {
            model.addAttribute("collaborators", collaborators);
        }

        return "form/details-form"; //
    }


}
