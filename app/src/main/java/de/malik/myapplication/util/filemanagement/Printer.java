// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.filemanagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import de.malik.myapplication.util.customermanagement.ProjectManager;

public class Printer {

    public void reprintFiles(ProjectManager projectManager) {
        ArrayList<ArrayList<String>> dataLists = projectManager.getPrintableDataLists();
        try {
            for (int i = 0; i < RSKFileManager.FILE_NAMES.length - 2; i++) {
                String fileName = RSKFileManager.FILE_NAMES[i];
                print(RSKFileManager.CREATED_FILES.get(fileName), false, dataLists.get(i));
            }
            print(RSKFileManager.CREATED_FILES.get(RSKFileManager.FILE_NAMES[5]), false, projectManager.getSavedCustomerNames());
            print(RSKFileManager.CREATED_FILES.get(RSKFileManager.FILE_NAMES[6]), false, projectManager.getSavedWorkDescriptions());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * prints all elements of <code>records</code> into <code>file</code>. Uses
     * <code>append</code> to decide if the already existing data will be overwritten or not
     * @param records an ArrayList containing all the String that will be printed into the file
     * @param file the file where the data will be printed into
     * @param append if true, the existing data will not get overwritten, otherwise it will
     * @throws IOException if the given file is a folder or if the file can not be found
     */
    public void print(File file, boolean append, ArrayList<String> records) throws IOException {
        PrintWriter writer = createWriter(file, append);
        for (String record : records) {
            writer.println(record);
        }
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }

    /**
     * creates a new instance of the <code>PrintWriter</code> class
     * @param file the file where the printer will print into
     * @param append if true, the existing data will not get overwritten, otherwise it will
     * @return a new instance of <code>PrintWriter</code> class
     * @throws IOException if the given file is a folder or if the file can not be found
     */
    private PrintWriter createWriter(File file, boolean append) throws IOException {
        FileWriter fWriter = new FileWriter(file, append);
        return new PrintWriter(new BufferedWriter(fWriter));

    }
}