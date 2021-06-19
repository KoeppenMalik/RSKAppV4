// Created on 01.03.2021, 20:31

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewarchivedprojects;

import android.view.View;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerUndoUnarchiveCustomer implements View.OnClickListener {

    private int index;
    private ProjectManager projectManager;
    private Project unarchivedProject;
    private RecyclerAdapterProjects recyclerAdapterProjects;

    public OnClickListenerUndoUnarchiveCustomer(int index, ProjectManager projectManager, Project unarchivedProject, RecyclerAdapterProjects recyclerAdapterProjects) {
        this.index = index;
        this.projectManager = projectManager;
        this.unarchivedProject = unarchivedProject;
        this.recyclerAdapterProjects = recyclerAdapterProjects;
    }

    @Override
    public void onClick(View v) {
        projectManager.getProjects().remove(projectManager.getProjects().lastIndexOf(unarchivedProject));
        projectManager.getArchivedProjects().add(index, unarchivedProject);
        recyclerAdapterProjects.notifyDataSetChanged();
    }
}
