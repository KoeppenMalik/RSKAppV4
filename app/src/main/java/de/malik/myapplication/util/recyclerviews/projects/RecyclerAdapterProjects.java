// Created on 30.01.2021, 18:57

package de.malik.myapplication.util.recyclerviews.projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Time;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.recyclerviews.Filter;
import de.malik.myapplication.util.recyclerviews.projects.ViewHolderProjects;
import de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects.OnClickListenerViewHolderProjects;

import java.util.ArrayList;

import static de.malik.myapplication.util.RSKSystem.TimeManager.*;

public class RecyclerAdapterProjects extends RecyclerView.Adapter<ViewHolderProjects> implements Filterable {

    private ArrayList<Project> allProjects;
    private ArrayList<Project> filteredProjects;
    private RSKSystem system;
    private View v;

    public RecyclerAdapterProjects(ArrayList<Project> allProjects, RSKSystem system) {
        this.allProjects = allProjects;
        filteredProjects = new ArrayList<>(allProjects);
        this.system = system;
    }

    @Override
    public ViewHolderProjects onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.viewholder_projects_recyclerview, parent, false);
        return new ViewHolderProjects(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderProjects holder, int position) {
        holder.setIsRecyclable(false);
        Project project = filteredProjects.get(position);
        Time startTime = project.getStartTime();
        Time stopTime = project.getStopTime();
        Time diff = getDiffInMinutes(startTime, stopTime, project.getPauses());

        holder.getTextViewName().setText(project.getName());
        holder.getTextViewWorkDescription().setText(project.getWorkDescription());
        holder.getTextViewTotalTime().setText(new Time(diff.getHours(), diff.getMinutes()).asDiffString());
        holder.getTextViewDate().setText(project.getDate());
        holder.getTextViewTimes().setText(project.getStartTime().asString("") + " - " + project.getStopTime().asString(""));
        v.setOnClickListener(new OnClickListenerViewHolderProjects(system, project));
    }

    @Override
    public int getItemCount() {
        return filteredProjects.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter(filteredProjects, allProjects, this);
    }

    public void setFilteredProjects(ArrayList<Project> filteredProjects) {
        this.filteredProjects = filteredProjects;
    }
}
