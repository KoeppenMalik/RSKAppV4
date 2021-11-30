package de.malik.myapplication.util.filter;

import java.util.ArrayList;
import java.util.Comparator;

import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.ProjectManager;

public class Filter {

    public static final String[] FILTER_LABELS = new String[] {
            "Datum (alt zu neu)", "Datum (neu zu alt)", "Name (A - Z)", "Erstellt (alt zu neu)"
    };
    public static final int[] FILTER_VALUE_TYPES = new int[] {
            ProjectManager.PROJECT_DATES, ProjectManager.PROJECT_DATES, ProjectManager.PROJECT_NAMES, ProjectManager.PROJECT_IDS
    };
    public static final FilterValue[] FILTER_ORIENTATIONS = new FilterValue[] {
            FilterValue.ASCENDING, FilterValue.DESCENDING, FilterValue.ASCENDING, FilterValue.ASCENDING
    };

    private long id;
    private String text;
    private int value;
    private FilterValue orientation;

    public Filter(long id, String text, int value, FilterValue orientation) {
        this.id = id;
        this.text = text;
        this.value = value;
        this.orientation = orientation;
    }

    private static ArrayList<String> order(FilterValue orientation, ArrayList<String> data) {
        if (orientation == FilterValue.ASCENDING)
            data.sort(Comparator.naturalOrder());
        else
            data.sort(Comparator.reverseOrder());
        return data;
    }

    public static void sortProjects(Filter newFilter) {
        ProjectManager.FilterHelper helper = new ProjectManager.FilterHelper();
        if (RSKSystem.currentFilter.getId() != newFilter.getId()) {
            ArrayList<String> data = helper.getProjectValues(newFilter.getValue());
            ArrayList<String> orderedData = order(newFilter.getOrientation(), data);
            ProjectManager.projects = helper.assignProjectsToValues(orderedData, newFilter.getValue());
        }
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }

    public FilterValue getOrientation() {
        return orientation;
    }
}