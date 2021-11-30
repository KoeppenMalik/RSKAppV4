// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.projectmanagement;

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

    public static final int PROJECT_IDS = 0;
    public static final int PROJECT_NAMES = 1;
    public static final int PROJECT_DATES = 4;

    public static ArrayList<Project> projects;
    private ArrayList<Project> archivedProjects;
    private ArrayList<Request> requests;
    private ArrayList<String> savedCustomerNames, savedWorkDescriptions;
    private ArrayList<ArrayList<String>> allRecords = new ArrayList<>();
    private ArrayList<ArrayList<? extends Unifiable>> allData = new ArrayList<>();

    public ProjectManager() {
        String[] fileNames = RSKFileManager.FILE_NAMES;
        try {
            // current projects
            projects = parseProjectsFromFile(fileNames[2], fileNames[0]);
            // archived projects
            archivedProjects = parseProjectsFromFile(fileNames[3], fileNames[1]);
            // requests
            ArrayList<String> lines = FileManager.getReader().readLines(FileManager.CREATED_FILES.get(fileNames[4]));
            requests = Parser.parseRequests(lines);
            // saved project names
            savedCustomerNames = FileManager.getReader().readLines(FileManager.CREATED_FILES.get(fileNames[5]));
            // saved work descriptions
            savedWorkDescriptions = FileManager.getReader().readLines(FileManager.CREATED_FILES.get(fileNames[6]));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        updateRecords();
    }

    private ArrayList<Project> parseProjectsFromFile(String pausesFileKey, String projectsFileKey) throws IOException {
        ArrayList<String> lines = FileManager.getReader().readLines(FileManager.CREATED_FILES.get(pausesFileKey));
        ArrayList<Pause> projectPauses = Parser.parsePauses(lines);
        lines = FileManager.getReader().readLines(FileManager.CREATED_FILES.get(projectsFileKey));
        return Parser.parseProjects(lines, projectPauses);
    }

    private void updateData() {
        allData.clear();
        allData.add(projects);
        allData.add(archivedProjects);
        allData.add(getPauses());
        allData.add(getArchivedPauses());
        allData.add(requests);
    }

    public void updateRecords() {
        allRecords.clear();
        updateData();
        for (int i = 0; i < allData.size(); i++) {
            ArrayList<String> records = new ArrayList<>();
            for (int j = 0; j < allData.get(i).size(); j++) {
                allData.get(i).get(j).updateRecord();
                records.add(allData.get(i).get(j).getRecord());
            }
            allRecords.add(records);
        }
    }

    public long getNextId(ArrayList<? extends Unifiable> list) {
        if (list.size() == 0) return 1;
        ArrayList<Long> ids = new ArrayList<>();
        for (Unifiable element : list) {
            ids.add(element.getId());
        }
        return Collections.max(ids) +1;
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

    public ArrayList<ArrayList<String>> getAllRecords() {
        return allRecords;
    }

    public ArrayList<Project> getArchivedProjects() {
        return archivedProjects;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public ArrayList<String> getSavedCustomerNames() {
        return savedCustomerNames;
    }

    public ArrayList<String> getSavedWorkDescriptions() {
        return savedWorkDescriptions;
    }

    // ------ Filter Helper class ------
    public static class FilterHelper {

        public ArrayList<String> getProjectValues(int value) {
            ArrayList<String> values = new ArrayList<>();
            for (Project project : ProjectManager.projects) {
                String[] data = project.getRecord().split(RSKFileManager.SPLIT_REGEX);
                values.add(data[value]);
            }
            return values;
        }

        public ArrayList<Project> assignProjectsToValues(ArrayList<String> values, int valueType) {
            ArrayList<Project> assigned = new ArrayList<>();
            for (String value : values) {
                for (Project project : ProjectManager.projects) {
                    String[] data = project.getRecord().split(RSKFileManager.SPLIT_REGEX);
                    if (data[valueType].equals(value) && !assigned.contains(project)) {
                        assigned.add(project);
                    }
                }
            }
            return assigned;
        }
    }
}
