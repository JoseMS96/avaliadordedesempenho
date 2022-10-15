package br.fai.add.api.controller;


import br.fai.add.api.service.impl.AnsweredQuestionRestServiceImpl;
import br.fai.add.model.entities.AnsweredQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/answeredQuestion")
@CrossOrigin(origins = "*")
public class AnsweredQuestionRestController {


    @Autowired
    AnsweredQuestionRestServiceImpl answeredQuestionRestService;

    @GetMapping("/find")
    public ResponseEntity<List<AnsweredQuestion>> find() {
        List<AnsweredQuestion> answeredQuestions = answeredQuestionRestService.find();

        if (answeredQuestions == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(answeredQuestions);
    }

}
