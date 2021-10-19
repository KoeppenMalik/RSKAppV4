package de.malik.myapplication.util.filter;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.mylibrary.managers.TimeManager;

public class Filter {

    private String text;
    private FilterValue value;

    public Filter(String text, FilterValue filterValue) {
        this.text = text;
        value = filterValue;
    }

    public static ArrayList<Project> sortProjects(FilterValue currentValue, FilterValue value, ArrayList<Project> projects) {
        ArrayList<Project> sortedList = new ArrayList<>();
        if (value == FilterValue.CREATED) {
            if (currentValue != FilterValue.CREATED) {
                ArrayList<Long> allIds = ProjectManager.getAllIds(projects);
                allIds.sort((o1, o2) -> {
                    return o1.compareTo(o2);
                });
                sortedList = ProjectManager.assignProjectsToIds(allIds, projects);
            } else return projects;
        }
        else if (value == FilterValue.DATE) {
            if (currentValue != FilterValue.DATE) {
                DateFormat df = new SimpleDateFormat(TimeManager.default_date_format, Locale.getDefault());
                ArrayList<String> allDates = ProjectManager.getAllDates(projects);
                allDates.sort((o1, o2) -> {
                    int result = 0;
                    try {
                        result = df.parse(o1).compareTo(df.parse(o2));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    return result;
                });
                sortedList = ProjectManager.assignProjectsToDates(allDates, projects);
            } else return projects;
        }
        else if (value == FilterValue.NAME) {
            if (currentValue != FilterValue.NAME) {
                ArrayList<String> allNames = ProjectManager.getAllNames(projects);
                allNames.sort((o1, o2) -> {
                    return o1.compareToIgnoreCase(o2);
                });
                sortedList = ProjectManager.assignProjectsToNames(allNames, projects);
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