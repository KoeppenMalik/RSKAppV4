// Created on 05.02.2021, 12:17

package de.malik.myapplication.listeners.onclick.addpausefragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;

public class OnClickListenerButtonCancel implements View.OnClickListener {

    private RSKSystem system;
    private Project project;

    public OnClickListenerButtonCancel(RSKSystem system, Project project) {
        this.system = system;
        this.project = project;
    }

    @Override
    public void onClick(View v) {
        system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_down);
    }
}
