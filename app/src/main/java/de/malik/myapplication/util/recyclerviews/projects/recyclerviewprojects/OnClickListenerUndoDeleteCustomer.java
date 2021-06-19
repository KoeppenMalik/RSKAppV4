// Created on 31.01.2021, 11:20

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects;

import android.view.View;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerUndoDeleteCustomer implements View.OnClickListener {

    private ProjectManager projectManager;
    private RecyclerAdapterProjects recyclerAdapterProjects;
    private int index;
    private Project deletedProject;

    public OnClickListenerUndoDeleteCustomer(ProjectManager projectManager, RecyclerAdapterProjects recyclerAdapterProjects, int index, Project deletedProject) {
        this.projectManager = projectManager;
        this.recyclerAdapterProjects = recyclerAdapterProjects;
        this.index = index;
        this.deletedProject = deletedProject;
    }

    @Override
    public void onClick(View v) {
        projectManager.getProjects().add(index, deletedProject);
        recyclerAdapterProjects.notifyDataSetChanged();
    }
}
