package br.fai.add.api.controller;

import br.fai.add.api.service.OptionRestService;
import br.fai.add.model.entities.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/option")
@CrossOrigin(origins = "*")
public class OptionRestController {

    @Autowired
    private OptionRestService<Option> optionService;


    @GetMapping("/find")
    public ResponseEntity<List<Option>> find() {
        List<Option> options = optionService.find();

        if (options == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(options);
    }

    @GetMapping("/findOptionsByQuestion/{id}")
    public ResponseEntity<List<Option>> findOptionsByQuestion(@PathVariable("id") final int id) {

        List<Option> options = optionService.findOptionsByQuestion(id);

        if (options == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(options);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Option> findById(@PathVariable("id") final int id) {

        Option option = optionService.findById(id);

        if (option == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(option);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = optionService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> create(@RequestBody final Option option) {

        int optionId = optionService.create(option);

        if (optionId == -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionId);
    }


}
