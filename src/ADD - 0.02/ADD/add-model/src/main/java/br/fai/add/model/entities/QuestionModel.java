package br.fai.add.model.entities;

public class QuestionModel {
    private int id;
    private String description;
    private boolean alternativeQuestion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAlternativeQuestion() {
        return alternativeQuestion;
    }

    public void setAlternativeQuestion(boolean alternativeQuestion) {
        this.alternativeQuestion = alternativeQuestion;
    }
}
