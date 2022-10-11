package br.fai.add.model.entities;

import java.sql.Timestamp;
import java.util.List;

public class Form extends BaseEntity {

    private Timestamp datetime;
    private Timestamp datelimit;
    private String title;
    private Collaborator collaborator;
    private List<Collaborator> employeeList;

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Timestamp getDatelimit() {
        return datelimit;
    }

    public void setDatelimit(Timestamp datelimit) {
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

    public List<Collaborator> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Collaborator> employeeList) {
        this.employeeList = employeeList;
    }
}
