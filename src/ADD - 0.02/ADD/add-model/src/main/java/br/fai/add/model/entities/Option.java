package br.fai.add.model.entities;

public class Option extends BaseEntity {

    private String description;
    private String option_label;
    private boolean correctAnswer;
    private Question question;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOption_label() {
        return option_label;
    }

    public void setOption_label(String option_label) {
        this.option_label = option_label;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
