package br.fai.add.client.controller;

import br.fai.add.client.service.RespondentService;
import br.fai.add.model.entities.Respondent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/respondent")
public class RespondentController {

    @Autowired
    private RespondentService respondentService;

    @GetMapping("/list")
    public String getRespondents(final Model model) {

        List<Respondent> respondents = respondentService.find();

        if (respondents == null || respondents.isEmpty()) {
            model.addAttribute("respondents", new ArrayList<Respondent>());
        } else {

            model.addAttribute("respondents", respondents);
        }

        return "";
    }

    @GetMapping("/detail/{id}")
    public String getRespondentDetailPage(@PathVariable("id") final int id, final Model model) {

        Respondent respondent = (Respondent) respondentService.findById(id);

        if (respondent == null) {
            return "common/not-found";
        }

        model.addAttribute("respondent", respondent);

        return "";
    }

    @PostMapping("/create")
    public String create(Respondent respondent) {

        respondentService.create(respondent);

        return "";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final int id, Model model) {

        respondentService.deleteById(id);


        return getRespondents(model);
    }
}
