// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.filemanagement;

import java.io.IOException;
import java.util.ArrayList;

import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.mylibrary.managers.FileManager;

public class Printer {

    public void reprintFiles(ProjectManager projectManager) {
        ArrayList<ArrayList<String>> dataLists = projectManager.getPrintableDataLists();
        try {
            for (int i = 0; i < RSKFileManager.FILE_NAMES.length - 2; i++) {
                String fileName = RSKFileManager.FILE_NAMES[i];
                FileManager.getPrinter().print(FileManager.CREATED_FILES.get(fileName), false, dataLists.get(i));
            }
            String record = projectManager.convertSavedProjectDataToPrintableString(projectManager.getSavedCustomerNames());
            FileManager.getPrinter().print(FileManager.CREATED_FILES.get(RSKFileManager.FILE_NAMES[5]), false, record);
            record = projectManager.convertSavedProjectDataToPrintableString(projectManager.getSavedWorkDescriptions());
            FileManager.getPrinter().print(FileManager.CREATED_FILES.get(RSKFileManager.FILE_NAMES[6]), false, record);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}