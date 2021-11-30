// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.filemanagement;

import java.io.IOException;
import java.util.ArrayList;

import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.mylibrary.managers.FileManager;

public class Printer {

    public void reprintFiles(ProjectManager projectManager) {
        ArrayList<ArrayList<String>> allRecords = projectManager.getAllRecords();
        try {
            for (int i = 0; i < allRecords.size(); i++) {
                FileManager.getPrinter().print(FileManager.CREATED_FILES.get(RSKFileManager.FILE_NAMES[i]), false, allRecords.get(i));
            }
            FileManager.getPrinter().print(FileManager.CREATED_FILES.get(RSKFileManager.FILE_NAMES[5]), false, projectManager.getSavedCustomerNames());
            FileManager.getPrinter().print(FileManager.CREATED_FILES.get(RSKFileManager.FILE_NAMES[6]), false, projectManager.getSavedWorkDescriptions());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}