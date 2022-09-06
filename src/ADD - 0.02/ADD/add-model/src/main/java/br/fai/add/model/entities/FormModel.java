package br.fai.add.model.entities;

public class FormModel {

    private int id;
    private String datetime;
    private String datelimit;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatahora() {
        return datetime;
    }

    public void setDatahora(String datahora) {
        this.datetime = datahora;
    }

    public String getDatelimit() {
        return datelimit;
    }

    public void setDatelimit(String datelimit) {
        this.datelimit = datelimit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
