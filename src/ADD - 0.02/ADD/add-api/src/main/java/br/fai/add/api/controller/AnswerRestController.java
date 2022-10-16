package br.fai.add.api.controller;


import br.fai.add.api.service.AnswerRestService;
import br.fai.add.model.entities.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
@CrossOrigin(origins = "*")
public class AnswerRestController {

    @Autowired
    private AnswerRestService<Answer> answerService;


    @GetMapping("/find")
    public ResponseEntity<List<Answer>> find() {
        List<Answer> answers = answerService.find();

        if (answers == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(answers);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Answer> findById(@PathVariable("id") final int id) {

        Answer answer = answerService.findById(id);

        if (answer == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(answer);
    }

    @GetMapping("/findAnswerByQuestion/{id}")
    public ResponseEntity<Answer> findAnswerByQuestion(@PathVariable("id") final int id) {

        Answer answer = answerService.findAnswerByQuestion(id);

        if (answer == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(answer);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = answerService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> create(@RequestBody final Answer answer) {

        int answerId = answerService.create(answer);

        if (answerId == -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(answerId);
    }


}
