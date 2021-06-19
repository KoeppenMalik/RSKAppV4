// Created on 01.03.2021, 20:48

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects;

import android.view.View;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerUndoArchiveCustomer implements View.OnClickListener {

    private int index;
    private RecyclerAdapterProjects recyclerAdapterProjects;
    private Project archivedProject;
    private ProjectManager projectManager;

    public OnClickListenerUndoArchiveCustomer(int index, RecyclerAdapterProjects recyclerAdapterProjects, Project archivedProject, ProjectManager projectManager) {
        this.index = index;
        this.recyclerAdapterProjects = recyclerAdapterProjects;
        this.archivedProject = archivedProject;
        this.projectManager = projectManager;
    }

    @Override
    public void onClick(View v) {
        projectManager.getArchivedProjects().remove(projectManager.getArchivedProjects().lastIndexOf(archivedProject));
        projectManager.getProjects().add(index, archivedProject);
        recyclerAdapterProjects.notifyDataSetChanged();
    }
}
