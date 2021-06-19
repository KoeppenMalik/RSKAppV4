// Created on 02.03.2021, 19:41

package de.malik.myapplication.util.customermanagement;

import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.filemanagement.FileManager;

public class Request {

    private long id;
    private String name, date, description;

    public Request(long id, String name, String date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
    }

    /**
     * creates a string with all the information of the request
     * @return a string with all information of the request divided by <code>FileManager.SPLIT_REGEX</code>
     */
    public String getRecord() {
        return id + FileManager.SPLIT_REGEX + name + FileManager.SPLIT_REGEX + date + FileManager.SPLIT_REGEX + description;
    }

    public Project toCustomer(RSKSystem system) {
        return new Project(system.getProjectManager().getNextCustomerId(), name, Time.EMPTY_TIME, Time.EMPTY_TIME, date, description);
    }

    public long getId() {
        return id;
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
