// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.customermanagement;

import de.malik.myapplication.util.filemanagement.FileManager;

import java.util.ArrayList;

public class Project {

    private long id;
    private String name, date, workDescription;
    private Time startTime, stopTime;
    private ArrayList<Pause> pauses = new ArrayList<>();

    public Project(long id, String name, Time startTime, Time stopTime, String date, String workDescription) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.date = date;
        this.workDescription = workDescription;
    }

    /**
     * creates a string which is ready to be printed into a file. it contains all the data of the customer
     * @return a string which can be printed into a file
     */
    public String getRecord() {
        return id + FileManager.SPLIT_REGEX + name + FileManager.SPLIT_REGEX + startTime.asString() + FileManager.SPLIT_REGEX + stopTime.asString() +
                FileManager.SPLIT_REGEX + date + FileManager.SPLIT_REGEX + workDescription;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getStopTime() {
        return stopTime;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Pause> getPauses() {
        return pauses;
    }

    public void setPauses(ArrayList<Pause> pauses) {
        this.pauses = pauses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setStopTime(Time stopTime) {
        this.stopTime = stopTime;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
