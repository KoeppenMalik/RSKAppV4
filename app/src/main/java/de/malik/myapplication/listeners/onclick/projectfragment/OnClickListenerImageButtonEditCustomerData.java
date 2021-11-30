// Created on 05.02.2021, 19:16

package de.malik.myapplication.listeners.onclick.projectfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.EditProjectDataFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.Project;

public class OnClickListenerImageButtonEditCustomerData implements View.OnClickListener {

    private RSKSystem system;
    private Project project;

    public OnClickListenerImageButtonEditCustomerData(RSKSystem system, Project project) {
        this.system = system;
        this.project = project;
    }

    @Override
    public void onClick(View v) {
        system.replaceCurrentFragmentWith(new EditProjectDataFragment(system, project), R.anim.slide_up);
    }
}
