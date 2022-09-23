package br.fai.add.api.controller;


import br.fai.add.api.service.CollaboratorRestService;
import br.fai.add.model.entities.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborator")
@CrossOrigin(origins = "*")
public class CollaboratorRestController {

    @Autowired
    private CollaboratorRestService<Collaborator> userService;


    //o find so esta aqui por didatica
    @GetMapping("/find")
    public ResponseEntity<List<Collaborator>> find() {
        List<Collaborator> collaborators = userService.find();

        if (collaborators == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(collaborators);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Collaborator> findById(@PathVariable("id") final int id) {

        Collaborator collaborator = userService.findById(id);

        if (collaborator == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(collaborator);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = userService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> create(@RequestBody final Collaborator collaborator) {

        int collaboratorId = userService.create(collaborator);

        if (collaboratorId == -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(collaboratorId);
    }


}
