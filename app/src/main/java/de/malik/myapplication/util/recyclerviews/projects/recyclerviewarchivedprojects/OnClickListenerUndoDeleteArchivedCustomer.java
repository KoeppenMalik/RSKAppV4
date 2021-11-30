// Created on 01.03.2021, 20:25

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewarchivedprojects;

import android.view.View;

import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.Project;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerUndoDeleteArchivedCustomer implements View.OnClickListener {

    private int index;
    private RSKSystem system;
    Project deletedProject;
    RecyclerAdapterProjects recyclerAdapterProjects;

    public OnClickListenerUndoDeleteArchivedCustomer(int index, RSKSystem system, Project deletedProject, RecyclerAdapterProjects recyclerAdapterProjects) {
        this.index = index;
        this.system = system;
        this.deletedProject = deletedProject;
        this.recyclerAdapterProjects = recyclerAdapterProjects;
    }

    @Override
    public void onClick(View v) {
        system.getProjectManager().getArchivedProjects().add(index, deletedProject);
        recyclerAdapterProjects.notifyDataSetChanged();
    }
}
