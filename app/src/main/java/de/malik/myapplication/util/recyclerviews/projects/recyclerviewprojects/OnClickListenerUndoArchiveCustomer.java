// Created on 01.03.2021, 20:48

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects;

import android.view.View;

import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.Project;
import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerUndoArchiveCustomer implements View.OnClickListener {

    private int index;
    private RecyclerAdapterProjects recyclerAdapterProjects;
    private Project archivedProject;
    private RSKSystem system;

    public OnClickListenerUndoArchiveCustomer(int index, RecyclerAdapterProjects recyclerAdapterProjects, Project archivedProject, RSKSystem system) {
        this.index = index;
        this.recyclerAdapterProjects = recyclerAdapterProjects;
        this.archivedProject = archivedProject;
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        system.getProjectManager().getArchivedProjects().remove(system.getProjectManager().getArchivedProjects().lastIndexOf(archivedProject));
        ProjectManager.projects.add(index, archivedProject);
        recyclerAdapterProjects.notifyDataSetChanged();
    }
}
