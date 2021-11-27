package de.malik.myapplication.util.filter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import de.malik.myapplication.util.RSKSystem;
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

    public static ArrayList<Project> sort(FilterValue newValue, ProjectManager projectManager) {
        ArrayList<Project> projects = projectManager.getProjects();
        if (RSKSystem.currentFilter.getValue() == newValue)
            return projectManager.getProjects();
        if (newValue == FilterValue.CREATED_ASC) {
            ArrayList<Long> ids = new ProjectManager.FilterHelper().getAllIds(projects);
            ids.sort((o1, o2) -> {
                return o1.compareTo(o2);
            });
            return new ProjectManager.FilterHelper().assignProjectsToIds(ids, projects);
        }
        else if (newValue == FilterValue.DATE_OLD_TO_NEW || newValue == FilterValue.DATE_NEW_TO_OLD) {
            DateFormat df = new SimpleDateFormat(TimeManager.default_date_format, Locale.GERMANY);
            ArrayList<String> dates = new ProjectManager.FilterHelper().getAllDates(projects);
            dates.sort((o1, o2) -> {
                try {
                    if (newValue == FilterValue.DATE_OLD_TO_NEW)
                        return df.parse(o1).compareTo(df.parse(o2));
                    return df.parse(o2).compareTo(df.parse(o1));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                return 0;
            });
            return new ProjectManager.FilterHelper().assignProjectsToDates(dates, projects);
        }
        else if (newValue == FilterValue.NAME_A_TO_Z) {
            ArrayList<String> names = new ProjectManager.FilterHelper().getAllNames(projects);
            names.sort((o1, o2) -> {
                return o1.compareTo(o2);
            });
            return new ProjectManager.FilterHelper().assignProjectsToNames(names, projects);
        }
        return projectManager.getProjects();
    }

    public String getText() {
        return text;
    }

    public FilterValue getValue() {
        return value;
    }
}