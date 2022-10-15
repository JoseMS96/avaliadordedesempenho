package br.fai.add.client.controller;

import br.fai.add.client.service.AnswerService;
import br.fai.add.model.entities.Answer;
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
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/list")
    public String getAnswers(final Model model) {

        List<Answer> answers = answerService.find();

        if (answers == null || answers.isEmpty()) {
            model.addAttribute("answers", new ArrayList<Answer>());
        } else {

            model.addAttribute("answers", answers);
        }

        return "form/view-form";
    }

    @GetMapping("/detail/{id}")
    public String getAnswerDetailPage(@PathVariable("id") final int id, final Model model) {

        Answer answer = (Answer) answerService.findById(id);

        if (answer == null) {
            return "common/not-found";
        }

        model.addAttribute("answer", answer);

        return ""; //pagina de vizualizar formulário com respostas dentro de perguntas
    }

    @PostMapping("/create-answer")
    public String create(Answer answer) {

        answerService.create(answer);

        return "form/question-form";// pagina atual para continuar respondendo perguntas

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final int id, Model model) {

        answerService.deleteById(id);


        return getAnswers(model);
    }
}
