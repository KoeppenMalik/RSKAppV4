// Created on 06.02.2021, 19:22

package de.malik.myapplication.listeners.onclick.overviewfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Time;
import de.malik.myapplication.util.customermanagement.Project;

public class OnClickListenerButtonAddCustomer implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerButtonAddCustomer(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        long id = system.getProjectManager().getNextCustomerId();
        String currentDate = RSKSystem.TimeManager.getCurrentDate();
        Project project = new Project(id, "Unbekannt " + id, Time.EMPTY_TIME, Time.EMPTY_TIME, currentDate, "-");
        system.getProjectManager().getProjects().add(project);
        system.getFileManager().getPrinter().reprintFiles(system.getFileManager(), system.getProjectManager());
        system.setRecentlyVisitedProject(project);
        system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_left);
    }
}