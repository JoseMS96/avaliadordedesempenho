package br.fai.add.model.entities;

public class Question extends BaseEntity {

    private String description;
    private boolean alternativesQuestion;
    private Form form;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAlternativesQuestion() {
        return alternativesQuestion;
    }

    public void setAlternativesQuestion(boolean alternativesQuestion) {
        this.alternativesQuestion = alternativesQuestion;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
