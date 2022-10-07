package br.fai.add.client.controller;

import br.fai.add.client.service.FormService;
import br.fai.add.model.entities.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/form")
public class FormController {


    @Autowired
    private FormService formService;

    @GetMapping("/answer-form")
    public String getAnswerFormPage() {
        return "form/answer-form";
    }


    @GetMapping("/list")
    public String getForms(final Model model) {

        List<Form> forms = formService.find();

        if (forms == null || forms.isEmpty()) {
            model.addAttribute("forms", new ArrayList<Form>());
        } else {

            model.addAttribute("forms", forms);
        }

        return "form/question-form"; //pagina para adicao de perguntas com os forms listados
    }

    @GetMapping("/detail/{id}")
    public String getFormDetailPage(@PathVariable("id") final int id, final Model model) {

        Form form = (Form) formService.findById(id);

        if (form == null) {
            return "form/view-form"; //pagina de vizualizar formulário com respostas dentro de perguntas
        }

        model.addAttribute("form", form);

        return "form/view-form"; //pagina de vizualizar formulário com respostas dentro de perguntas
    }

    @GetMapping("/create-form")
    public String create(Form form) {

        formService.create(form);
        return "create-form"; //pagina para criar formulários até voltar?
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final int id, Model model) {

        formService.deleteById(id);


        return getForms(model);
    }

//    @GetMapping("/pending-form")
//    public String getPendingFormPage() {
//        return "form/pending-form";
//    }
//
//    @GetMapping("/view-form")
//    public String getViewFormPage() {
//        return "form/view-form";
//    }

}
