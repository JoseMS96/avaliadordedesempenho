package br.fai.add.client.controller;

import br.fai.add.client.service.*;
import br.fai.add.client.service.impl.AnsweredQuestionServiceImpl;
import br.fai.add.model.entities.*;
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
    private OptionService optionService;
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

    @Autowired
    private AnsweredQuestionServiceImpl answeredQuestionService;

    @GetMapping("/create-form-page")
    public String getFormCreatePage(final Model model, HttpSession session) {

        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");

        List<Form> forms = formService.findAllForms(collaborator_reviewer.getId());

        if (forms == null || forms.isEmpty()) {
            model.addAttribute("forms", new ArrayList<Form>());
        } else {

            model.addAttribute("forms", forms);
        }

        model.addAttribute("currentUser", collaborator_reviewer);

        return "form/create-form";
    }

    @PostMapping("/create-form")
    public String create(Form form, HttpSession session) {

        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");

        Collaborator collaborator = (Collaborator) collaboratorService.findById(collaborator_reviewer.getId());

        form.setCollaborator(collaborator);

        formService.create(form);

        return "redirect:/form/create-form-page";
    }

    @GetMapping("/form-details/{id}")
    public String getFormDetailPage(@PathVariable("id") final int id, final Model model, HttpSession session) {

        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");
        Form form = (Form) formService.findById(id);

        model.addAttribute("form", form);


        List<Question> questions = questionService.findQuestionsByForm(id);

        if (questions == null || questions.isEmpty()) {
            model.addAttribute("questions", new ArrayList<Question>());
        } else {
            model.addAttribute("questions", questions);
        }

        List<Respondent> respondents = respondentService.findRespondentsByForm(id);

        if (respondents == null || respondents.isEmpty()) {
            model.addAttribute("respondents", new ArrayList<Respondent>());
        } else {
            model.addAttribute("respondents", respondents);
        }

        model.addAttribute("currentUser", collaborator_reviewer);
        return "form/details-form"; //
    }

    @GetMapping("/view-form-question/{id}/{formId}")
    public String getPageViewForm(@PathVariable("id") final int id, @PathVariable("formId") final int formId, final Model model, HttpSession session) {

        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");

        Respondent respondent = (Respondent) respondentService.findById(id);
        Collaborator collaborator = (Collaborator) collaboratorService.findById(respondent.getCollaborator().getId());
        model.addAttribute("respondent", respondent);


        Form form = (Form) formService.findById(formId);
        model.addAttribute("form", form);

        List<AnsweredQuestion> answeredQuestions = answeredQuestionService.find(formId, collaborator.getId());

        if (answeredQuestions == null || answeredQuestions.isEmpty()) {
            model.addAttribute("answeredQuestions", new ArrayList<AnsweredQuestion>());
        } else {
            model.addAttribute("answeredQuestions", answeredQuestions);
        }

        model.addAttribute("currentUser", collaborator_reviewer);
        return "form/view-form";
    }

    @GetMapping("/add-employee-page/{id}")
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
        model.addAttribute("currentUser", collaborator_reviewer);

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


    @GetMapping("/add-question-page/{id}")
    public String getAddQuestionPage(@PathVariable("id") final int id, final Model model, HttpSession session) {
        Form form = (Form) formService.findById(id);

        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");

        model.addAttribute("form", form);

        model.addAttribute("currentUser", collaborator_reviewer);
        return "form/question-form";
    }

    @PostMapping("/add-question")
    public String create(Question question, @RequestParam("formId") final int formId) {

        Form form = (Form) formService.findById(formId);

        question.setForm(form);

        int id = questionService.create(question);

        if (question.isAlternativesQuestion() == true) {
            return "redirect:/form/option-test/" + id + "/" + formId;
        } else {
            return "redirect:/form/form-details/" + formId;
        }
    }

    @GetMapping("/option-test/{id}/{formId}")
    public String getOptionTestPG(@PathVariable("id") final int id,
                                  @PathVariable("formId") final int formId, final Model model,HttpSession session) {
        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");
        Question question = (Question) questionService.findById(id);
        model.addAttribute("question", question);

        Form form = (Form) formService.findById(formId);
        model.addAttribute("form", form);
        model.addAttribute("currentUser", collaborator_reviewer);
        return "form/add-option";
    }

    @PostMapping("/option-create")
    public String create(Option option, @RequestParam("formId") final int formId, @RequestParam("questionId") final int questionId ) {

        Question question = (Question) questionService.findById(questionId);

        option.setQuestion(question);

        optionService.create(option);

        return "redirect:/form/option-test/" + questionId + "/" + formId;
    }

    @GetMapping("/answer-form/{id}")
    public String getAnswerFormPage(@PathVariable("id") final int id, final Model model, HttpSession session) {
        Collaborator collaborator_reviewer = (Collaborator) session.getAttribute("currentUser");

        Form form = (Form) formService.findById(id);
        model.addAttribute("form", form);


        List<Question> questions = questionService.findQuestionsByForm(id);

        if (questions == null || questions.isEmpty()) {
            model.addAttribute("questions", new ArrayList<Question>());
        } else {
            model.addAttribute("questions", questions);
        }


        model.addAttribute("currentUser", collaborator_reviewer);
        return "form/answer-form";
    }

    @GetMapping("/answer-question/{id}")
    public String getAnswerQuestionPage(@PathVariable("id") final int id, final Model model, HttpSession session) {
        Collaborator collaborator_employee = (Collaborator) session.getAttribute("currentUser");

        Question question = (Question) questionService.findById(id);
        model.addAttribute("question", question);

        if (question.isAlternativesQuestion() == true) {
            List<Option> options = optionService.findOptionsByQuestion(id);

            if (options == null || options.isEmpty()) {
                model.addAttribute("options", new ArrayList<Option>());
            } else {
                model.addAttribute("options", options);
            }
        }


        model.addAttribute("currentUser", collaborator_employee);
        return "form/answer-question";
    }

    @PostMapping("/create-answer")
    public String CreateAnswer(HttpSession session, final Model model, @RequestParam("questionId") final int questionId, Answer answer) {
        Collaborator collaborator_employee = (Collaborator) session.getAttribute("currentUser");

        Question question = (Question) questionService.findById(questionId);

        int formId = question.getForm().getId();

        answer.setCollaborator(collaborator_employee);
        answer.setQuestion(question);

        Respondent respondent = (Respondent) respondentService.findRespondentByCollaboratorAndForm(collaborator_employee.getId(), formId);
        respondentService.update(respondent.getId(), respondent);


        answerService.create(answer);

        model.addAttribute("currentUser", collaborator_employee);
        return "redirect:answer-form/" + formId;
    }

    @PostMapping("create-alternative-answer")
    public String CreateAlternativeAnswer(HttpSession session, final Model model,
                                          @RequestParam("questionId") final int questionId,
                                          Answer answer, @RequestParam("optionId") final int optionId) {
        Collaborator collaborator_employee = (Collaborator) session.getAttribute("currentUser");

        Question question = (Question) questionService.findById(questionId);

        Option option = (Option) optionService.findById(optionId);

        int formId = question.getForm().getId();

        answer.setOption(option);
        answer.setCollaborator(collaborator_employee);
        answer.setQuestion(question);

        Respondent respondent = (Respondent) respondentService.
                findRespondentByCollaboratorAndForm(collaborator_employee.getId(), formId);
        respondentService.update(respondent.getId(), respondent);


        answerService.create(answer);

        model.addAttribute("currentUser", collaborator_employee);
        return "redirect:answer-form/" + formId;

    }


}
