// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.customermanagement;


import de.malik.myapplication.util.filemanagement.FileManager;
import de.malik.myapplication.util.filemanagement.Parser;
import de.malik.myapplication.util.filemanagement.Reader;

import java.util.ArrayList;
import java.util.Collections;

public class ProjectManager {

    private ArrayList<Project> projects, archivedProjects;
    private ArrayList<Request> requests;
    private ArrayList<String> savedCustomerNames, savedWorkDescriptions;

    public ProjectManager(FileManager fileManager) {
        // current customers
        ArrayList<String> lines = Reader.readLines(fileManager.getPausesFile());
        ArrayList<Pause> pauses = Parser.parsePauses(lines);
        lines = Reader.readLines(fileManager.getCustomersFile());
        projects = Parser.parseProjects(lines, pauses);
        // archived customers
        lines = Reader.readLines(fileManager.getArchivedPausesFile());
        pauses = Parser.parsePauses(lines);
        lines = Reader.readLines(fileManager.getArchiveFile());
        archivedProjects = Parser.parseProjects(lines, pauses);
        // requests
        lines = Reader.readLines(fileManager.getRequestsFile());
        requests = Parser.parseRequests(lines);
        // saved customer names
        lines = Reader.readLines(fileManager.getSavedCustomerNamesFile());
        savedCustomerNames = Parser.parseStrings(lines);
        // saved work descriptions
        lines = Reader.readLines(fileManager.getSavedWorkDescriptionsFile());
        savedWorkDescriptions = Parser.parseStrings(lines);
    }

    public int getPositionOf(String element, ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(element)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * gets a list of all current customers
     * @return an arraylist of all current customers
     */
    public ArrayList<Project> getProjects() {
        return projects;
    }

    /**
     * creates an arraylist of all current pauses
     * @return an arraylist which contains all current pauses
     */
    public ArrayList<Pause> getPauses() {
        ArrayList<Pause> allPauses = new ArrayList<>();
        for (Project project : projects) {
            allPauses.addAll(project.getPauses());
        }
        return allPauses;
    }

    /**
     * creates an arraylist of all archived pauses
     * @return an arraylist which contains all archived pauses
     */
    public ArrayList<Pause> getArchivedPauses() {
        ArrayList<Pause> allPauses = new ArrayList<>();
        for (Project project : archivedProjects) {
            allPauses.addAll(project.getPauses());
        }
        return allPauses;
    }

    /**
     * gets a list of all archived customers
     * @return an arraylist of all archived customers
     */
    public ArrayList<Project> getArchivedProjects() {
        return archivedProjects;
    }

    /**
     * gets a list of all requests
     * @return an arraylist of all requests customers
     */
    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    /**
     * gets a list of all saved customer names
     * @return an arraylist of all saved customer names
     */
    public ArrayList<String> getSavedCustomerNames() {
        return savedCustomerNames;
    }

    public ArrayList<String> getSavedWorkDescriptions() {
        return savedWorkDescriptions;
    }

    public long getNextPauseId() {
        if (getPauses().isEmpty()) {
            return 1;
        }
        ArrayList<Long> ids = new ArrayList<>();

        for (Pause pause : getPauses()) {
            ids.add(pause.getId());
        }
        long id = Collections.max(ids);
        return ++id;
    }

    public long getNextCustomerId() {
        if (projects.isEmpty()) {
            return 1;
        }
        ArrayList<Long> ids = new ArrayList<>();

        for (Project project : projects) {
            ids.add(project.getId());
        }
        long id = Collections.max(ids);
        return ++id;
    }

    public long getNextRequestId() {
        if (requests.isEmpty()) {
            return 1;
        }
        ArrayList<Long> ids = new ArrayList<>();

        for (Request request : requests) {
            ids.add(request.getId());
        }
        long id = Collections.max(ids);
        return ++id;
    }
}
