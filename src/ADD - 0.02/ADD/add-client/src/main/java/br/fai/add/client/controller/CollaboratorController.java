package br.fai.add.client.controller;

import br.fai.add.client.service.CollaboratorService;
import br.fai.add.model.entities.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/collaborator")
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;


    @GetMapping("/list")
    public String getCollaborators(final Model model) {

        List<Collaborator> collaborators = collaboratorService.find();

        if (collaborators == null || collaborators.isEmpty()) {
            model.addAttribute("collaborators", new ArrayList<Collaborator>());
        } else {
            model.addAttribute("collaborators", collaborators);
        }

        return "form/details-form"; //retornar pagina atual para adicionar colaborador ao form?
    }

    @GetMapping("/detail/{id}")
    public String getDetailPage(@PathVariable("id") final int id, final Model model, HttpSession session) {

        Collaborator collaborator = (Collaborator) collaboratorService.findById(id);
        Collaborator currentUser = (Collaborator) session.getAttribute("currentUser");


        if (collaborator == null) {
            // futuramente iremos injetar o erro
            return "refirect:/collaborator/list";
        }

        model.addAttribute("collaborator", collaborator);
        model.addAttribute("currentUser",currentUser);
        return "collaborator/detail";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final int id, Model model) {

        collaboratorService.deleteById(id);


        return getCollaborators(model);
    }

}


