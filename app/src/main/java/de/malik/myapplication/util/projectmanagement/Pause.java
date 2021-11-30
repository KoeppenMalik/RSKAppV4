// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.projectmanagement;

import java.util.Date;

import de.malik.mylibrary.managers.TimeManager;

public class Pause extends Unifiable {

    private Date time;

    public Pause(long id, Date time) {
        super(id+"", TimeManager.toTimeString(time, false));
        setId(id);
        this.time = time;
    }

    @Override
    public void updateRecord() {
        super.createPrintableString(null, getId()+"", TimeManager.toTimeString(time, false));
    }

    public Date getTime() {
        return time;
    }
}
