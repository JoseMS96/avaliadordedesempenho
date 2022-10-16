package br.fai.add.api.controller;


import br.fai.add.api.service.impl.AnsweredQuestionRestServiceImpl;
import br.fai.add.model.entities.AnsweredQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answeredQuestion")
@CrossOrigin(origins = "*")
public class AnsweredQuestionRestController {


    @Autowired
    AnsweredQuestionRestServiceImpl answeredQuestionRestService;

    @GetMapping("/find/{formId}/{collaboratorId}")
    public ResponseEntity<List<AnsweredQuestion>> find(@PathVariable("formId") final int formId, @PathVariable("collaboratorId") final int collaboratorId) {
        List<AnsweredQuestion> answeredQuestions = answeredQuestionRestService.find(formId, collaboratorId);

        if (answeredQuestions == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(answeredQuestions);
    }

}
