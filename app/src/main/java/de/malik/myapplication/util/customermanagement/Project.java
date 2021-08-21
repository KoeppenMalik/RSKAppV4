// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.customermanagement;

import de.malik.myapplication.util.filemanagement.RSKFileManager;
import de.malik.mylibrary.managers.TimeManager;

import java.util.ArrayList;
import java.util.Date;

public class Project {

    private long id;
    private String name, date, workDescription;
    private Date startTime, stopTime;
    private ArrayList<Pause> pauses = new ArrayList<>();

    public Project(long id, String name, Date startTime, Date stopTime, String date, String workDescription) {
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
        String record = id + RSKFileManager.SPLIT_REGEX + name + RSKFileManager.SPLIT_REGEX + TimeManager.toTimeString(startTime, false) + RSKFileManager.SPLIT_REGEX + TimeManager.toTimeString(stopTime, false) +
                RSKFileManager.SPLIT_REGEX + date + RSKFileManager.SPLIT_REGEX + workDescription;
        for (Pause pause : pauses) {
            record += RSKFileManager.SPLIT_REGEX + pause.getId();
        }
        return record;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getStopTime() {
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

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setStopTime(Date stopTime) {
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
