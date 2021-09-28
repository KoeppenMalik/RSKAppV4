// Created on 26.02.2021, 14:51

package de.malik.myapplication.listeners.onclick.overviewfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerViewRecentlyVisitedCustomer implements View.OnClickListener{

    private RSKSystem system;

    public OnClickListenerViewRecentlyVisitedCustomer(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        if (system.getRecentlyVisitedProject() != null) {
            system.replaceCurrentFragmentWith(new ProjectFragment(system, system.getRecentlyVisitedProject()), R.anim.slide_right);
        }
        else system.showErrorDialog("Es wurde noch kein Projekt ausgewählt.");
    }
}
