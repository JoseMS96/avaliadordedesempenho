package br.fai.add.api.controller;


import br.fai.add.api.service.UserRestService;
import br.fai.add.model.entities.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "*")
public class AccountRestController {

    @Autowired
    private UserRestService userRestService;


    @PostMapping("/login")
    public ResponseEntity<Collaborator> login(@RequestParam("username") final String username,
                                              @RequestParam("password") final String password) {
        Collaborator collaborator = userRestService.validateLogin(username, password);

        if (collaborator == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(collaborator);
    }

}
