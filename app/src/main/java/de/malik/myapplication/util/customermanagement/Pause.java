// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.customermanagement;

import java.util.Date;

import de.malik.myapplication.util.filemanagement.RSKFileManager;
import de.malik.mylibrary.managers.TimeManager;

public class Pause {

    private long id;
    private Date time;

    public Pause(long id, Date time) {
        this.id = id;
        this.time = time;
    }

    /**
     * creates a string which is ready to be printed into a file. it contains all the data of the pause
     * @return a string which can be printed into a file
     */
    public String getRecord() {
        return id + RSKFileManager.SPLIT_REGEX + TimeManager.toTimeString(time, false);
    }

    public long getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }
}
