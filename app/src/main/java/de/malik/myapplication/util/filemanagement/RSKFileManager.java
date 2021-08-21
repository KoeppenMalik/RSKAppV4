// Created on 29.01.2021, 16:46

package de.malik.myapplication.util.filemanagement;

import java.io.File;
import java.io.IOException;

import de.malik.mylibrary.managers.FileManager;

public class RSKFileManager {

    public static final String COMMENT_PREFIX = "##";
    public static final String SPLIT_REGEX = ",";
    public static final String[] FILE_NAMES = {
            "currentProjects.csv", "archivedProjects.csv",
            "currentProjectsPauses.csv", "archivedProjectsPauses.csv",
            "requests.csv",
            "savedProjectNames.csv", "savedWorkDescriptions.csv"
    };
    
    private Printer printer;

    public RSKFileManager(String folderDir, Printer printer) {
        this.printer = printer;
        try {
            createFiles(folderDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void createFiles(String folderDir) throws IOException {
        File folder = FileManager.createFolder("rskData", folderDir);
        for (String fileName : FILE_NAMES)
            FileManager.createFile(fileName, folder);
        FileManager.createFile("temp.csv", folder);
    }

    public Printer getPrinter() {
        return printer;
    }
}