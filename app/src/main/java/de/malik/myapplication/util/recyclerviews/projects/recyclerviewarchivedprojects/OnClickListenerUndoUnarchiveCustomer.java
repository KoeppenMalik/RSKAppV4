// Created on 01.03.2021, 20:31

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewarchivedprojects;

import android.view.View;

import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.Project;
import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerUndoUnarchiveCustomer implements View.OnClickListener {

    private int index;
    private RSKSystem system;
    private Project unarchivedProject;
    private RecyclerAdapterProjects recyclerAdapterProjects;

    public OnClickListenerUndoUnarchiveCustomer(int index, RSKSystem system, Project unarchivedProject, RecyclerAdapterProjects recyclerAdapterProjects) {
        this.index = index;
        this.system = system;
        this.unarchivedProject = unarchivedProject;
        this.recyclerAdapterProjects = recyclerAdapterProjects;
    }

    @Override
    public void onClick(View v) {
        ProjectManager.projects.remove(ProjectManager.projects.lastIndexOf(unarchivedProject));
        system.getProjectManager().getArchivedProjects().add(index, unarchivedProject);
        recyclerAdapterProjects.notifyDataSetChanged();
    }
}
