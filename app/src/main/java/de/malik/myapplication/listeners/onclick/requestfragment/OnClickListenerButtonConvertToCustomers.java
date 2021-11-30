// Created on 09.03.2021, 21:09

package de.malik.myapplication.listeners.onclick.requestfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.Project;
import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.myapplication.util.projectmanagement.Request;

public class OnClickListenerButtonConvertToCustomers implements View.OnClickListener {

    private RSKSystem system;
    private Request request;

    public OnClickListenerButtonConvertToCustomers(RSKSystem system, Request request) {
        this.system = system;
        this.request = request;
    }

    @Override
    public void onClick(View v) {
        Project project = request.toProject(system);
        ProjectManager.projects.add(project);
        system.saveData();
        system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_right);
    }
}