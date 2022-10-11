package br.fai.add.client.controller;

import br.fai.add.client.service.OrganizationService;
import br.fai.add.model.entities.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/list-org-reviewer")
    public String getOrganizations(final Model model) {

        List<Organization> organizations = organizationService.find();

        if (organizations == null || organizations.isEmpty()) {
            model.addAttribute("organizations", new ArrayList<Organization>());
        } else {

            model.addAttribute("organizations", organizations);
        }


        return ""; //para a pagina dos dropdowns DOS REVIEWERS
    }


    @GetMapping("/list-org-employee")
    public String getOrganizations2(final Model model) {

        List<Organization> organizations = organizationService.find();

        if (organizations == null || organizations.isEmpty()) {
            model.addAttribute("organizations", new ArrayList<Organization>());
        } else {

            model.addAttribute("organizations", organizations);
        }

        return ""; //para a pagina dos dropdowns DOS FUNCIONÁRIOS
    }

    //metodo create esta no accountcontroller

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final int id, Model model) {

        organizationService.deleteById(id);


        return getOrganizations(model);
    }

}
