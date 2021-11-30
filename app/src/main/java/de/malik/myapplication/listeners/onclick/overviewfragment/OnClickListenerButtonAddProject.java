// Created on 06.02.2021, 19:22

package de.malik.myapplication.listeners.onclick.overviewfragment;

import android.view.View;

import java.util.Date;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.Project;
import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.mylibrary.managers.TimeManager;

public class OnClickListenerButtonAddProject implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerButtonAddProject(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        ProjectManager projectManager = system.getProjectManager();
        long id = projectManager.getNextId(ProjectManager.projects);
        String currentDate = TimeManager.currentDate();
        Project project = new Project(id, "Unbekannt " + id, new Date(0), new Date(0), currentDate, "-");
        ProjectManager.projects.add(project);
        system.saveData();
        system.setRecentlyVisitedProject(project);
        system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_left);
    }
}