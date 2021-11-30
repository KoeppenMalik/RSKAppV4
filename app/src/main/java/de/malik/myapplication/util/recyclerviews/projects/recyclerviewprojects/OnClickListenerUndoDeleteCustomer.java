// Created on 31.01.2021, 11:20

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects;

import android.view.View;

import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.Project;
import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerUndoDeleteCustomer implements View.OnClickListener {

    private RecyclerAdapterProjects recyclerAdapterProjects;
    private int index;
    private Project deletedProject;

    public OnClickListenerUndoDeleteCustomer(RecyclerAdapterProjects recyclerAdapterProjects, int index, Project deletedProject) {
        this.recyclerAdapterProjects = recyclerAdapterProjects;
        this.index = index;
        this.deletedProject = deletedProject;
    }

    @Override
    public void onClick(View v) {
        ProjectManager.projects.add(index, deletedProject);
        recyclerAdapterProjects.notifyDataSetChanged();
    }
}
