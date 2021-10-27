// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.customermanagement;

import de.malik.myapplication.util.filemanagement.Parser;
import de.malik.myapplication.util.filemanagement.RSKFileManager;
import de.malik.myapplication.util.filemanagement.RSKReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class ProjectManager {

    private ArrayList<Project> projects, archivedProjects;
    private ArrayList<Request> requests;
    private ArrayList<String> savedCustomerNames, savedWorkDescriptions;

    public ProjectManager() {
        ArrayList<String> lines;
        ArrayList<Pause> pauses;
        Map<String, File> createdFiles = RSKFileManager.CREATED_FILES;
        String[] fileNames = RSKFileManager.FILE_NAMES;
        try {
            // current projects
            lines = RSKReader.readLines(createdFiles.get(fileNames[2]));
            pauses = Parser.parsePauses(lines);
            lines = RSKReader.readLines(createdFiles.get(fileNames[0]));
            projects = Parser.parseProjects(lines, pauses);
            // archived projects
            lines = RSKReader.readLines(createdFiles.get(fileNames[3]));
            pauses = Parser.parsePauses(lines);
            lines = RSKReader.readLines(createdFiles.get(fileNames[1]));
            archivedProjects = Parser.parseProjects(lines, pauses);
            // requests
            lines = RSKReader.readLines(createdFiles.get(fileNames[4]));
            requests = Parser.parseRequests(lines);
            // saved project names
            savedCustomerNames = RSKReader.readLines(createdFiles.get(fileNames[5]));
            // saved work descriptions
            savedWorkDescriptions = RSKReader.readLines(createdFiles.get(fileNames[6]));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Long> getAllIds(ArrayList<Project> projects) {
        ArrayList<Long> allIds = new ArrayList<>();
        for (Project project : projects) {
            allIds.add(project.getId());
        }
        return allIds;
    }

    public static ArrayList<Project> assignProjectsToIds(ArrayList<Long> ids, ArrayList<Project> projects) {
        ArrayList<Project> assignedProjects = new ArrayList<>();
        ArrayList<Long> ids1 = new ArrayList<>(ids);
        for (long id : ids) {
            for (Project project : projects) {
                if (ids1.contains(id)) {
                    if (id == project.getId()) {
                        assignedProjects.add(project);
                        ids1.remove(id);
                        projects.remove(project);
                        break;
                    }
                }
            }
        }
        return assignedProjects;
    }

    public static ArrayList<String> getAllNames(ArrayList<Project> projects) {
        ArrayList<String> allNames = new ArrayList<>();
        for (Project project : projects) {
            allNames.add(project.getName());
        }
        return allNames;
    }

    public static ArrayList<Project> assignProjectsToNames(ArrayList<String> names, ArrayList<Project> projects) {
        ArrayList<Project> assignedProjects = new ArrayList<>();
        ArrayList<String> names1 = new ArrayList<>(names);
        for (String name : names) {
            for (Project project : projects) {
                if (names1.contains(name)) {
                    if (name.equals(project.getName())) {
                        assignedProjects.add(project);
                        names1.remove(name);
                        projects.remove(project);
                        break;
                    }
                }
            }
        }
        return assignedProjects;
    }

    public static ArrayList<String> getAllDates(ArrayList<Project> projects) {
        ArrayList<String> dates = new ArrayList<>();
        for (Project project : projects) {
            dates.add(project.getDate());
        }
        return dates;
    }

    public static ArrayList<Project> assignProjectsToDates(ArrayList<String> dates, ArrayList<Project> projects) {
        ArrayList<Project> assignedProjects = new ArrayList<>();
        ArrayList<String> dates1 = new ArrayList<>(dates);
        for (String date : dates) {
            for (Project project : projects) {
                if (dates1.contains(date)) {
                    if (date.equals(project.getDate())) {
                        assignedProjects.add(project);
                        dates1.remove(date);
                        projects.remove(project);
                        break;
                    }
                }
            }
        }
        return assignedProjects;
    }

    public ArrayList<ArrayList<String>> getPrintableDataLists() {
        ArrayList<ArrayList<String>> dataLists = new ArrayList<>();
        ArrayList<String> projects = new ArrayList<>(),
                archivedProjects = new ArrayList<>(),
                pauses = new ArrayList<>(),
                archivedPauses = new ArrayList<>(),
                requests = new ArrayList<>();

        for (Project project : this.projects) {
            projects.add(project.getRecord());
        }
        for (Project archivedProject : this.archivedProjects) {
            archivedProjects.add(archivedProject.getRecord());
        }
        for (Pause pause : getPauses()) {
            pauses.add(pause.getRecord());
        }
        for (Pause archivedPause : getArchivedPauses()) {
            archivedPauses.add(archivedPause.getRecord());
        }
        for (Request request : this.requests) {
            requests.add(request.getRecord());
        }
        dataLists.add(projects);
        dataLists.add(archivedProjects);
        dataLists.add(pauses);
        dataLists.add(archivedPauses);
        dataLists.add(requests);
        return dataLists;
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

    public ArrayList<Project> getArchivedProjects() {
        return archivedProjects;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

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
