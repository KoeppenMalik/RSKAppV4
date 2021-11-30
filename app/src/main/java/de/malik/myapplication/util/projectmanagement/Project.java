// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.projectmanagement;

import java.util.ArrayList;
import java.util.Date;

import de.malik.mylibrary.managers.TimeManager;

public class Project extends Unifiable {

    private String name, date, workDescription;
    private Date startTime, stopTime;
    private ArrayList<Pause> pauses = new ArrayList<>();

    public Project(long id, String name, Date startTime, Date stopTime, String date, String workDescription) {
        super( id+"", name, TimeManager.toTimeString(startTime, false), TimeManager.toTimeString(stopTime, false), date, workDescription);
        setId(id);
        this.name = name;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.date = date;
        this.workDescription = workDescription;
    }

    @Override
    public void updateRecord() {
        super.createPrintableString(getPauses(),getId()+"", name, TimeManager.toTimeString(startTime, false), TimeManager.toTimeString(stopTime, false), date, workDescription);
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
