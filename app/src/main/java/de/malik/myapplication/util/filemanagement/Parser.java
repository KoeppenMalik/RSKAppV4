// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.filemanagement;

import de.malik.myapplication.util.customermanagement.Request;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.Pause;
import de.malik.mylibrary.managers.TimeManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Parser {

    /**
     * parses each element of lines to a new instance of the class customer. Also parses the equivalent pause to each customer
     * @param lines an arraylist of the lines which each contain a customer
     * @param allPauses an arraylist of pauses
     * @return a fully parsed arraylist of the class Customer
     */
    public static ArrayList<Project> parseProjects(ArrayList<String> lines, ArrayList<Pause> allPauses) {
        ArrayList<Project> projects = new ArrayList<>();
        Project project;

        for (String line : lines) {
            if (!line.contains(RSKFileManager.COMMENT_PREFIX)) {
                String[] rows = line.split(RSKFileManager.SPLIT_REGEX);
                Date startTime = new Date(TimeManager.toMillis(rows[2]));
                Date stopTime = new Date(TimeManager.toMillis(rows[3]));
                project = new Project(Integer.parseInt(rows[0]), rows[1], startTime, stopTime, rows[4], rows[5]);
                if (rows.length > 6) {
                    ArrayList<Pause> pauses = new ArrayList<>();
                    for (int i = 6; i < rows.length; i++) {
                        for (int j = 0; j < allPauses.size(); j++) {
                            if (Long.parseLong(rows[i]) == allPauses.get(j).getId()) {
                                pauses.add(allPauses.get(j));
                                break;
                            }
                        }
                    }
                    project.setPauses(pauses);
                }
                projects.add(project);
            }
        }
        return projects;
    }

    /**
     * parses each element of lines to a new pause
     * @param lines an arraylist of the lines which each contain a pause
     * @return a fully parsed arraylist of the class Pause
     */
    public static ArrayList<Pause> parsePauses(ArrayList<String> lines) {
        ArrayList<Pause> pauses = new ArrayList<>();

        for (String line : lines) {
            if (!line.contains(RSKFileManager.COMMENT_PREFIX)) {
                String[] rows = line.split(RSKFileManager.SPLIT_REGEX);
                Date time = new Date(TimeManager.toMillis(rows[1]));
                pauses.add(new Pause(Long.parseLong(rows[0]), time));
            }
        }
        return pauses;
    }

    /**
     * parses each element of lines to a new instance of Request
     * @param lines an arraylist of the lines which each contain a request
     * @return a fully parsed arraylist of the class Request
     */
    public static ArrayList<Request> parseRequests(ArrayList<String> lines) {
        ArrayList<Request> requests = new ArrayList<>();

        for (String line : lines) {
            if (!line.contains(RSKFileManager.COMMENT_PREFIX)) {
                String[] rows = line.split(RSKFileManager.SPLIT_REGEX);
                requests.add(new Request(Long.parseLong(rows[0]), rows[1], rows[2], rows[3]));
            }
        }
        return requests;
    }
}