package br.fai.add.model.entities;

public class Form extends BaseEntity {

    private String datetime;
    private String datelimit;
    private String title;
    private Collaborator collaborator;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }
}
