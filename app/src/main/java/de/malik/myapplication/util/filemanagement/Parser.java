// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.filemanagement;

import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Request;
import de.malik.myapplication.util.customermanagement.Time;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.Pause;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
            if (!line.contains(FileManager.COMMENT_PREFIX)) {
                String[] rows = line.split(FileManager.SPLIT_REGEX);
                Time startTime = RSKSystem.TimeManager.parseTime(rows[2]);
                Time stopTime = RSKSystem.TimeManager.parseTime(rows[3]);
                project = new Project(Integer.parseInt(rows[0]), rows[1], startTime, stopTime, rows[4], rows[5]);
                if (rows.length > 7) {
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
            if (!line.contains(FileManager.COMMENT_PREFIX)) {
                String[] rows = line.split(FileManager.SPLIT_REGEX);
                Time time = RSKSystem.TimeManager.parseTime(rows[1]);
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
            if (!line.contains(FileManager.COMMENT_PREFIX)) {
                String[] rows = line.split(FileManager.SPLIT_REGEX);
                requests.add(new Request(Long.parseLong(rows[0]), rows[1], rows[2], rows[3]));
            }
        }
        return requests;
    }

    public static ArrayList<String> parseStrings(ArrayList<String> lines) {
        ArrayList<String> strings = new ArrayList<>();
        if (lines.size() == 0) {
            return strings;
        }
        String[] splitStrings = null;
        for (String line : lines) {
            splitStrings = line.split(FileManager.SPLIT_REGEX);
        }
        strings.addAll(Arrays.asList(splitStrings));
        return strings;
    }
}
