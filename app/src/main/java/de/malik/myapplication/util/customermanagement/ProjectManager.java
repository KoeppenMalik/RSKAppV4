// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.customermanagement;


import android.util.Log;

import de.malik.myapplication.util.filemanagement.Parser;
import de.malik.myapplication.util.filemanagement.RSKFileManager;
import de.malik.mylibrary.managers.FileManager;
import de.malik.mylibrary.utils.UtilsLibFileReader;

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
        UtilsLibFileReader reader = FileManager.getReader();
        Map<String, File> createdFiles = FileManager.CREATED_FILES;
        String[] fileNames = RSKFileManager.FILE_NAMES;
        try {
            // current projects
            lines = FileManager.getReader().readLines(createdFiles.get(fileNames[2]));
            pauses = Parser.parsePauses(lines);
            lines = FileManager.getReader().readLines(createdFiles.get(fileNames[0]));
            projects = Parser.parseProjects(lines, pauses);
            // archived projects
            lines = reader.readLines(createdFiles.get(fileNames[3]));
            pauses = Parser.parsePauses(lines);
            lines = reader.readLines(createdFiles.get(fileNames[1]));
            archivedProjects = Parser.parseProjects(lines, pauses);
            // requests
            lines = reader.readLines(createdFiles.get(fileNames[4]));
            requests = Parser.parseRequests(lines);
            // saved project names
            savedCustomerNames = reader.readLines(createdFiles.get(fileNames[5]));
            // saved work descriptions
            savedWorkDescriptions = reader.readLines(createdFiles.get(fileNames[6]));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
            Log.i("TAG", pause.getRecord());
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

    // ---- Filter Helper class ----

    public static class FilterHelper {

        public ArrayList<Long> getAllIds(ArrayList<Project> projects) {
            ArrayList<Long> ids = new ArrayList<>();
            for (Project project : projects) {
                ids.add(project.getId());
            }
            return ids;
        }

        public ArrayList<Project> assignProjectsToIds(ArrayList<Long> ids, ArrayList<Project> projects) {
            ArrayList<Project> assignedProjects = new ArrayList<>();
            for (Long id : ids) {
                for (Project proj : projects) {
                    if (id == proj.getId()) {
                        assignedProjects.add(proj);
                        break;
                    }
                }
            }
            return assignedProjects;
        }

        public ArrayList<String> getAllDates(ArrayList<Project> projects) {
            ArrayList<String> dates = new ArrayList<>();
            for (Project project : projects) {
                dates.add(project.getDate());
            }
            return dates;
        }

        public ArrayList<Project> assignProjectsToDates(ArrayList<String> dates, ArrayList<Project> projects) {
            ArrayList<Project> assignedProjects = new ArrayList<>();
            for (String date : dates) {
                for (Project proj : projects) {
                    if (!assignedProjects.contains(proj) && date.equals(proj.getDate())) {
                        assignedProjects.add(proj);
                        break;
                    }
                }
            }
            return assignedProjects;
        }

        public ArrayList<String> getAllNames(ArrayList<Project> projects) {
            ArrayList<String> names = new ArrayList<>();
            for (Project project : projects) {
                names.add(project.getName());
            }
            return names;
        }

        public ArrayList<Project> assignProjectsToNames(ArrayList<String> names, ArrayList<Project> projects) {
            ArrayList<Project> assignedProjects = new ArrayList<>();
            for (String name : names) {
                for (Project proj : projects) {
                    if (!assignedProjects.contains(proj) && name.equals(proj.getName())) {
                        assignedProjects.add(proj);
                    }
                }
            }
            return assignedProjects;
        }
    }
}
