package br.fai.add.api.controller;

import br.fai.add.api.service.RespondentRestService;
import br.fai.add.model.entities.Respondent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respondent")
@CrossOrigin(origins = "*")
public class RespondentRestController {

    @Autowired
    private RespondentRestService<Respondent> respondentService;


    @GetMapping("/find")
    public ResponseEntity<List<Respondent>> find() {
        List<Respondent> respondents = respondentService.find();

        if (respondents == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(respondents);
    }

    @GetMapping("/findRespondentsByForm/{id}")
    public ResponseEntity<List<Respondent>> findRespondentsByForm(@PathVariable("id") final int id) {

        List<Respondent> respondents = respondentService.findRespondentsByForm(id);

        if (respondents == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(respondents);
    }

    @GetMapping("/findRespondentsByOrg/{id}")
    public ResponseEntity<List<Respondent>> findRespondentsByOrg(@PathVariable("id") final int id) {

        List<Respondent> respondents = respondentService.findRespondentsByOrg(id);

        if (respondents == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(respondents);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Respondent> findById(@PathVariable("id") final int id) {

        Respondent respondent = respondentService.findById(id);

        if (respondent == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(respondent);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") final int id, @RequestBody final Respondent entity) {

        boolean response = respondentService.update(id, entity);

        return response ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = respondentService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> create(@RequestBody final Respondent respondent) {

        int respondentId = respondentService.create(respondent);

        if (respondentId == -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(respondentId);
    }


}
