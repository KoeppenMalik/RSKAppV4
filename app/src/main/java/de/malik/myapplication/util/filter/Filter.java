package de.malik.myapplication.util.filter;

import java.util.ArrayList;
import java.util.Comparator;

import de.malik.myapplication.util.customermanagement.Project;

public class Filter {

    private String text;
    private FilterValue value;

    public Filter(String text, FilterValue filterValue) {
        this.text = text;
        value = filterValue;
    }

    public static ArrayList<Project> sort(FilterValue currentValue, FilterValue value, ArrayList<Project> projects) {
        ArrayList<Project> sortedList = new ArrayList<>();
        if (value == FilterValue.NEW_TO_OLD) {
            if (!(currentValue == FilterValue.NEW_TO_OLD)) {
                for (int i = projects.size() - 1; i >= 0; i--) {
                    sortedList.add(projects.get(i));
                }
            } else return projects;
        }
        else if (value == FilterValue.OLD_TO_NEW) {
            if (!(currentValue == FilterValue.OLD_TO_NEW)) {
                for (int i = projects.size() - 1; i >= 0; i--) {
                    sortedList.add(projects.get(i));
                }
            } else return projects;
        }
        return sortedList;
    }

    public String getText() {
        return text;
    }

    public FilterValue getValue() {
        return value;
    }
}