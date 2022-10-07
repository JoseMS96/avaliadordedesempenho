package br.fai.add.client.controller;

import br.fai.add.client.service.OptionService;
import br.fai.add.model.entities.Option;
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
@RequestMapping("/option")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping("/list")
    public String getOptions(final Model model) {

        List<Option> options = optionService.find();

        if (options == null || options.isEmpty()) {
            model.addAttribute("options", new ArrayList<Option>());
        } else {

            model.addAttribute("options", options);
        }


        return ""; //talvez seja necessário esse metodo, talvez não
    }

    @PostMapping("/create-option")
    public String create(Option option) {

        optionService.create(option);

        return "";// prox pag para criar avaliador

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final int id, Model model) {

        optionService.deleteById(id);


        return getOptions(model);
    }


}
