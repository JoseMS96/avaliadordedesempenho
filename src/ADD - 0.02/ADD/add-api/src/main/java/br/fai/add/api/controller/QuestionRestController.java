package br.fai.add.api.controller;

import br.fai.add.api.service.QuestionRestService;
import br.fai.add.model.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@CrossOrigin(origins = "*")
public class QuestionRestController {

    @Autowired
    private QuestionRestService<Question> questionService;


    @GetMapping("/find")
    public ResponseEntity<List<Question>> find() {
        List<Question> questions = questionService.find();

        if (questions == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(questions);
    }

    @GetMapping("/findQuestionsByForm/{id}")
    public ResponseEntity<List<Question>> findQuestionsByForm(@PathVariable("id") final int id) {

        List<Question> questions = questionService.findQuestionsByForm(id);

        if (questions == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(questions);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Question> findById(@PathVariable("id") final int id) {

        Question question = questionService.findById(id);

        if (question == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = questionService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> create(@RequestBody final Question question) {

        int questionId = questionService.create(question);

        if (questionId == -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(questionId);
    }


}
