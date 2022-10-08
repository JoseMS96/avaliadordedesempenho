package br.fai.add.client.controller;

import br.fai.add.client.service.QuestionService;
import br.fai.add.model.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/list")
    public String getQuestions(final Model model) {

        List<Question> questions = questionService.find();

        if (questions == null || questions.isEmpty()) {
            model.addAttribute("questions", new ArrayList<Question>());
        } else {

            model.addAttribute("questions", questions);
        }

        return "form/details-form"; //pagina que lista as perguntas
    }

    @PostMapping("/create-question")
    public String create(Question question) {

        questionService.create(question);

        return "form/details-form";//

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final int id, Model model) {

        questionService.deleteById(id);


        return getQuestions(model);
    }

}
