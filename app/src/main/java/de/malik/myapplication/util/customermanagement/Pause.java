// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.customermanagement;

import de.malik.myapplication.util.filemanagement.FileManager;

public class Pause {

    private long id;
    private Time time;

    public Pause(long id, Time time) {
        this.id = id;
        this.time = time;
    }

    /**
     * creates a string which is ready to be printed into a file. it contains all the data of the pause
     * @return a string which can be printed into a file
     */
    public String getRecord() {
        return id + FileManager.SPLIT_REGEX + time.asString();
    }

    public long getId() {
        return id;
    }

    public Time getTime() {
        return time;
    }
}
