package br.fai.add.api.controller;

import br.fai.add.api.service.FormRestService;
import br.fai.add.model.entities.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/form")
@CrossOrigin(origins = "*")
public class FormRestController {

    @Autowired
    private FormRestService<Form> formService;


    @GetMapping("/find")
    public ResponseEntity<List<Form>> find() {
        List<Form> forms = formService.find();

        if (forms == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(forms);
    }

    @GetMapping("/findAllForms/{id}")
    public ResponseEntity<List<Form>> findAllForms(@PathVariable("id") final int id) {

        List<Form> forms = formService.findAllForms(id);

        if (forms == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(forms);
    }

    @GetMapping("/findAnsweredForms/{id}")
    public ResponseEntity<List<Form>> findAnsweredForms(@PathVariable("id") final int id) {
        List<Form> forms = formService.findAnsweredForms(id);

        if (forms == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(forms);
    }

    @GetMapping("/findUnansweredForms/{id}")
    public ResponseEntity<List<Form>> findUnansweredForms(@PathVariable("id") final int id) {
        List<Form> forms = formService.findUnansweredForms(id);

        if (forms == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(forms);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Form> findById(@PathVariable("id") final int id) {

        Form form = formService.findById(id);

        if (form == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(form);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = formService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> create(@RequestBody final Form form) {

        int formId = formService.create(form);

        if (formId == -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(formId);
    }


}
