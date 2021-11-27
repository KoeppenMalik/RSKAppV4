// Created on 28.02.2021, 15:06

package de.malik.myapplication.listeners.onclick.overviewfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ArchivedProjectsFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerViewArchivedCustomers implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerViewArchivedCustomers(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        if (system.getProjectManager().getArchivedProjects().size() > 0) {
            system.replaceCurrentFragmentWith(new ArchivedProjectsFragment(system), R.anim.slide_up);
        }
        else system.showErrorDialog("Derzeit gibt es keine archivierten Projekte.");
    }
}
