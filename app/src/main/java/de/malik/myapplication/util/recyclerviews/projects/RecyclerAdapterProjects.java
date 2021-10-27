// Created on 30.01.2021, 18:57

package de.malik.myapplication.util.recyclerviews.projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Pause;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.TimeManager;
import de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects.OnClickListenerViewHolderProjects;

import java.util.ArrayList;
import java.util.Date;

public class RecyclerAdapterProjects extends RecyclerView.Adapter<ViewHolderProjects> {

    private ArrayList<Project> projects;
    private RSKSystem system;
    private View v;

    public RecyclerAdapterProjects(ArrayList<Project> projects, RSKSystem system) {
        this.projects = projects;
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
        Project project = projects.get(position);
        Date startTime = project.getStartTime();
        Date stopTime = project.getStopTime();
        long diff = TimeManager.diff(stopTime, startTime);
        for (Pause pause : project.getPauses()) {
            diff -= pause.getTime().getTime();
        }
        Date timeDiff = new Date(diff);

        holder.getTextViewName().setText(project.getName());
        holder.getTextViewWorkDescription().setText(project.getWorkDescription());
        holder.getTextViewTotalTime().setText(TimeManager.toTimeString(timeDiff, false));
        holder.getTextViewDate().setText(project.getDate());
        holder.getTextViewTimes().setText(TimeManager.formatTimeString(TimeManager.toTimeString(startTime, false)) + " - " + TimeManager.formatTimeString(TimeManager.toTimeString(stopTime, false)));
        v.setOnClickListener(new OnClickListenerViewHolderProjects(system, project));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}
