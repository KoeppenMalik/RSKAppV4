// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.filemanagement;

import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.customermanagement.Pause;
import de.malik.myapplication.util.customermanagement.Request;

import java.io.*;
import java.util.ArrayList;

public class Printer {

    private File customersFile, pausesFile, archiveFile, archivedPausesFile, requestsFile, savedCustomerNamesFile, savedWorkDescriptionsFile, tempFile;
    private ProjectManager projectManager;

    public void reprintFiles(FileManager fileManager, ProjectManager projectManager) {
        this.projectManager = projectManager;
        customersFile = fileManager.getCustomersFile();
        pausesFile = fileManager.getPausesFile();
        archiveFile = fileManager.getArchiveFile();
        archivedPausesFile = fileManager.getArchivedPausesFile();
        requestsFile = fileManager.getRequestsFile();
        savedCustomerNamesFile = fileManager.getSavedCustomerNamesFile();
        savedWorkDescriptionsFile = fileManager.getSavedWorkDescriptionsFile();
        tempFile = fileManager.getTempFile();
        reprintCustomersFile();
        reprintPausesFile();
        reprintArchivedCustomersFile();
        reprintArchivedPausesFile();
        reprintRequestsFile();
        reprintSavedCustomerNamesFile();
        reprintSavedWorkDescriptionsFile();
    }

    public void reprintSavedWorkDescriptionsFile() {
        ArrayList<String> savedWorkDescriptions = projectManager.getSavedWorkDescriptions();
        PrintWriter writer = null;

        try {
            writer = createPrinter(tempFile);
            for (int i = 0; i < savedWorkDescriptions.size(); i++) {
                if (i == savedWorkDescriptions.size() -1)
                    writer.print(savedWorkDescriptions.get(i));
                else writer.print(savedWorkDescriptions.get(i) + FileManager.SPLIT_REGEX);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            savedWorkDescriptionsFile.delete();
            tempFile.renameTo(savedWorkDescriptionsFile);
        }
    }

    public void reprintSavedCustomerNamesFile() {
        ArrayList<String> savedCustomerNames = projectManager.getSavedCustomerNames();
        PrintWriter writer = null;

        try {
            writer = createPrinter(tempFile);
            for (int i = 0; i < savedCustomerNames.size(); i++) {
                if (i == savedCustomerNames.size() -1)
                    writer.print(savedCustomerNames.get(i));
                else writer.print(savedCustomerNames.get(i) + FileManager.SPLIT_REGEX);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            savedCustomerNamesFile.delete();
            tempFile.renameTo(savedCustomerNamesFile);
        }
    }

    /**
     * prints all data of the customers list into a new temporary file. afterwords the file will be deleted and renamed to the original customers file
     */
    private void reprintCustomersFile() {
        ArrayList<Project> projects = projectManager.getProjects();
        PrintWriter writer = null;

        try {
            writer = createPrinter(tempFile);
            for (Project project : projects) {
                writer.println(project.getRecord() + RSKSystem.getSplittedIds(project.getPauses()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            customersFile.delete();
            tempFile.renameTo(customersFile);
        }
    }

    private void reprintPausesFile() {
        ArrayList<Pause> pauses = projectManager.getPauses();
        PrintWriter writer = null;

        try {
            writer = createPrinter(tempFile);
            for (Pause pause : pauses) {
                writer.println(pause.getRecord());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            pausesFile.delete();
            tempFile.renameTo(pausesFile);
        }
    }

    private void reprintArchivedCustomersFile() {
        ArrayList<Project> archivedProjects = projectManager.getArchivedProjects();
        PrintWriter writer = null;

        try {
            writer = createPrinter(tempFile);
            for (Project project : archivedProjects) {
                writer.println(project.getRecord() + RSKSystem.getSplittedIds(project.getPauses()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            archiveFile.delete();
            tempFile.renameTo(archiveFile);
        }
    }

    private void reprintArchivedPausesFile() {
        ArrayList<Pause> archivedPauses = projectManager.getArchivedPauses();
        PrintWriter writer = null;

        try {
            writer = createPrinter(tempFile);
            for (Pause pause : archivedPauses) {
                writer.println(pause.getRecord());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            archivedPausesFile.delete();
            tempFile.renameTo(archivedPausesFile);
        }
    }

    private void reprintRequestsFile() {
        ArrayList<Request> requests = projectManager.getRequests();
        PrintWriter writer = null;

        try {
            writer = createPrinter(tempFile);
            for (Request request : requests) {
                writer.println(request.getRecord());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            requestsFile.delete();
            tempFile.renameTo(requestsFile);
        }
    }

    private PrintWriter createPrinter(File file) throws IOException {
        FileWriter fWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        return new PrintWriter(bWriter);
    }
}
