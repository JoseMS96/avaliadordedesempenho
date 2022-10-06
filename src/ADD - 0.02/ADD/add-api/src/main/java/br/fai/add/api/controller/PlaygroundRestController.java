package br.fai.add.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playground")
@CrossOrigin(origins = "*")
//Este é para liberar acesso
public class PlaygroundRestController {

    @GetMapping("/openapi-test")
    public ResponseEntity<String> getOpenApi() {
        return ResponseEntity.ok("teste com o OpenApi");
    }
}