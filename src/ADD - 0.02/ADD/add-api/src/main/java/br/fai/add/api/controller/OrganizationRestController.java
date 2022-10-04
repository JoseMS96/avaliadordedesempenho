package br.fai.add.api.controller;

import br.fai.add.api.service.OrganizationRestService;
import br.fai.add.model.entities.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
@CrossOrigin(origins = "*")
public class OrganizationRestController {

    @Autowired
    private OrganizationRestService<Organization> organizationService;


    @GetMapping("/find")
    public ResponseEntity<List<Organization>> find() {
        List<Organization> organizations = organizationService.find();

        if (organizations == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Organization> findById(@PathVariable("id") final int id) {

        Organization organization = organizationService.findById(id);

        if (organization == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(organization);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = organizationService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> create(@RequestBody final Organization organization) {

        int organizationId = organizationService.create(organization);

        if (organizationId == -1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(organizationId);
    }


}
