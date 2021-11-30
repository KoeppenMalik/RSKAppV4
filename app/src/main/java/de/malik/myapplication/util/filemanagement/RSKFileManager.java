// Created on 29.01.2021, 16:46

package de.malik.myapplication.util.filemanagement;

import java.io.IOException;

import de.malik.mylibrary.managers.FileManager;

public class RSKFileManager {

    public static final String COMMENT_PREFIX = "##";
    public static final String SPLIT_REGEX = "  ";
    public static final String FOLDER_NAME = "rsk_data";

    public static final String[] FILE_NAMES = {
            "current_projects.csv", "archived_projects.csv",
            "current_projects_pauses.csv", "archived_projects_pauses.csv",
            "requests.csv",
            "saved_project_names.csv", "saved_work_descriptions.csv"
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
        FileManager.createFolder(FOLDER_NAME, folderDir);
        for (String fileName : FILE_NAMES)
            FileManager.createFile(fileName, FileManager.CREATED_FOLDERS.get(FOLDER_NAME));
    }

    public Printer getPrinter() {
        return printer;
    }
}