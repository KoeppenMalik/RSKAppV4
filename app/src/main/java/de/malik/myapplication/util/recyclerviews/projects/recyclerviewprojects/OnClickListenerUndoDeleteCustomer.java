// Created on 31.01.2021, 11:20

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects;

import android.view.View;

import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerUndoDeleteCustomer implements View.OnClickListener {

    private RSKSystem system;
    private RecyclerAdapterProjects recyclerAdapterProjects;
    private int index;
    private Project deletedProject;

    public OnClickListenerUndoDeleteCustomer(RSKSystem system, RecyclerAdapterProjects recyclerAdapterProjects, int index, Project deletedProject) {
        this.system = system;
        this.recyclerAdapterProjects = recyclerAdapterProjects;
        this.index = index;
        this.deletedProject = deletedProject;
    }

    @Override
    public void onClick(View v) {
        system.getProjectManager().getProjects().add(index, deletedProject);
        recyclerAdapterProjects.notifyDataSetChanged();
    }
}
