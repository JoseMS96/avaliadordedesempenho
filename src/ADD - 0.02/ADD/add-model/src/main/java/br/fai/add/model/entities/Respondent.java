package br.fai.add.model.entities;


import java.util.List;

public class Respondent extends BaseEntity {
    private boolean Is_Active;
    private String name;
    private List<Collaborator> collaboratorList;
    private Form form;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isIs_Active() {
        return Is_Active;
    }

    public void setIs_Active(boolean is_Active) {
        Is_Active = is_Active;
    }

    public List<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    public void setCollaboratorList(List<Collaborator> collaboratorList) {
        this.collaboratorList = collaboratorList;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
