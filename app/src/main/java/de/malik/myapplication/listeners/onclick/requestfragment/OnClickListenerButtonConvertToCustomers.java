// Created on 09.03.2021, 21:09

package de.malik.myapplication.listeners.onclick.requestfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.customermanagement.Request;

public class OnClickListenerButtonConvertToCustomers implements View.OnClickListener {

    private RSKSystem system;
    private Request request;

    public OnClickListenerButtonConvertToCustomers(RSKSystem system, Request request) {
        this.system = system;
        this.request = request;
    }

    @Override
    public void onClick(View v) {
        ProjectManager projectManager = system.getProjectManager();
        Project project = request.toCustomer(system);

        projectManager.getProjects().add(project);
        projectManager.getRequests().remove(request);
        system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
        system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_right);
    }
}