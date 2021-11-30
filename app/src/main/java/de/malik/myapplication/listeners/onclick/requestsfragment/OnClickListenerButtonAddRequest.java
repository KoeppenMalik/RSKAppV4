// Created on 03.03.2021, 21:10

package de.malik.myapplication.listeners.onclick.requestsfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.RequestFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.myapplication.util.projectmanagement.Request;
import de.malik.mylibrary.managers.TimeManager;

public class OnClickListenerButtonAddRequest implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerButtonAddRequest(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        ProjectManager projectManager = system.getProjectManager();
        long id = projectManager.getNextId(projectManager.getRequests());
        Request request = new Request(id, "Unbekannt " + id, TimeManager.currentDate(), "-");
        system.getProjectManager().getRequests().add(request);
        system.saveData();
        system.replaceCurrentFragmentWith(new RequestFragment(system, request), R.anim.slide_up);
    }
}
