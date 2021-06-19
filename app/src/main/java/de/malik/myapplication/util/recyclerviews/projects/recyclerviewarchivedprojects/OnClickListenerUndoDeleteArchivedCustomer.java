// Created on 01.03.2021, 20:25

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewarchivedprojects;

import android.view.View;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerUndoDeleteArchivedCustomer implements View.OnClickListener {

    private int index;
    ProjectManager projectManager;
    Project deletedProject;
    RecyclerAdapterProjects recyclerAdapterProjects;

    public OnClickListenerUndoDeleteArchivedCustomer(int index, ProjectManager projectManager, Project deletedProject, RecyclerAdapterProjects recyclerAdapterProjects) {
        this.index = index;
        this.projectManager = projectManager;
        this.deletedProject = deletedProject;
        this.recyclerAdapterProjects = recyclerAdapterProjects;
    }

    @Override
    public void onClick(View v) {
        projectManager.getArchivedProjects().add(index, deletedProject);
        recyclerAdapterProjects.notifyDataSetChanged();
    }
}
