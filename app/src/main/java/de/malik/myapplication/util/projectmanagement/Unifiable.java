package de.malik.myapplication.util.projectmanagement;

import java.util.ArrayList;

import de.malik.myapplication.util.filemanagement.RSKFileManager;

public abstract class Unifiable {

    private String record;
    private long id;

    public Unifiable(String... args) {
        createPrintableString(null, args);
    }

    public abstract void updateRecord();

    public void createPrintableString(ArrayList<Pause> projectPauses, String... args) {
        record = "";
        for (int i = 0; i < args.length; i++) {
            if (i == args.length -1)
                record += args[i];
            else
                record += args[i] + RSKFileManager.SPLIT_REGEX;
        }
        if (projectPauses != null) {
            for (Pause pause : projectPauses) {
                record += RSKFileManager.SPLIT_REGEX + pause.getId();
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecord() {
        return record;
    }
}
