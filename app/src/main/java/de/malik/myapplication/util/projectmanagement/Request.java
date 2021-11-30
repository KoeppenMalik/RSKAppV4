// Created on 02.03.2021, 19:41

package de.malik.myapplication.util.projectmanagement;

import java.util.Date;

import de.malik.myapplication.util.RSKSystem;

public class Request extends Unifiable {

    private String name, date, description;

    public Request(long id, String name, String date, String description) {
        super(id+"", name, date, description);
        setId(id);
        this.name = name;
        this.date = date;
        this.description = description;
    }

    @Override
    public void updateRecord() {
        super.createPrintableString(null, getId()+"", name, date, description);
    }

    public Project toProject(RSKSystem system) {
        ProjectManager projectManager = system.getProjectManager();
        return new Project(projectManager.getNextId(ProjectManager.projects), name, new Date(0), new Date(0), date, description);
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
