// Created on 06.02.2021, 12:52

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;

public class OnClickListenerViewHolderProjects implements View.OnClickListener {

    private RSKSystem system;
    private Project project;

    public OnClickListenerViewHolderProjects(RSKSystem system, Project project) {
        this.system = system;
        this.project = project;
    }

    @Override
    public void onClick(View v) {
        system.setRecentlyVisitedProject(project);
        system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_up);
    }
}
