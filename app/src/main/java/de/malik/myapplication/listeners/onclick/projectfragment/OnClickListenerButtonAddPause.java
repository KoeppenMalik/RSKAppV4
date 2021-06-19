// Created on 01.02.2021, 09:41

package de.malik.myapplication.listeners.onclick.projectfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.AddPauseFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;

public class OnClickListenerButtonAddPause implements View.OnClickListener {

    private RSKSystem system;
    private Project project;

    public OnClickListenerButtonAddPause(RSKSystem system, Project project) {
        this.system = system;
        this.project = project;
    }

    @Override
    public void onClick(View v) {
        system.replaceCurrentFragmentWith(new AddPauseFragment(project, system), R.anim.slide_up);
    }
}
