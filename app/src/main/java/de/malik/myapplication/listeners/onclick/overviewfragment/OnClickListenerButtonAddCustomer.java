// Created on 06.02.2021, 19:22

package de.malik.myapplication.listeners.onclick.overviewfragment;

import android.view.View;

import java.util.Date;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.mylibrary.managers.TimeManager;

public class OnClickListenerButtonAddCustomer implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerButtonAddCustomer(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        long id = system.getProjectManager().getNextCustomerId();
        String currentDate = TimeManager.currentDate();
        Project project = new Project(id, "Unbekannt " + id, new Date(0), new Date(0), currentDate, "-");
        system.getProjectManager().getProjects().add(project);
        system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
        system.setRecentlyVisitedProject(project);
        system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_left);
    }
}