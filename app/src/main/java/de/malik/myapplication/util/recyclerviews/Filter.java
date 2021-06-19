// Created on 31.01.2021, 11:50

package de.malik.myapplication.util.recyclerviews;

import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

import java.util.ArrayList;
import java.util.List;

public class Filter extends android.widget.Filter {

    private ArrayList<Project> filteredProjects, allProjects;
    private RecyclerAdapterProjects recyclerAdapter;

    public Filter(ArrayList<Project> filteredProjects, ArrayList<Project> allProjects, RecyclerAdapterProjects recyclerAdapter) {
        this.filteredProjects = filteredProjects;
        this.allProjects = allProjects;
        this.recyclerAdapter = recyclerAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        ArrayList<Project> results = new ArrayList<>();

        if (constraint == null || constraint.length() == 0) {
            results.addAll(allProjects);
        }
        else {
            String filterConstraint = constraint.toString().toLowerCase().trim();
            for (Project project : allProjects) {
                if (project.getName().toLowerCase().contains(filterConstraint)) {
                    results.add(project);
                }
            }
        }
        FilterResults filterResults = new FilterResults();
        filterResults.values = results;
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        filteredProjects.clear();
        filteredProjects.addAll((List) results.values);
        recyclerAdapter.notifyDataSetChanged();
    }
}
