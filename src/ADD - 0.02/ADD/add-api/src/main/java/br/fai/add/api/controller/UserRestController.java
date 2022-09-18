package br.fai.add.api.controller;


import br.fai.add.api.service.UserRestService;
import br.fai.add.model.entities.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborator")
@CrossOrigin(origins = "*")
public class UserRestController {

    @Autowired
    private UserRestService<Collaborator> userService;


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


}
