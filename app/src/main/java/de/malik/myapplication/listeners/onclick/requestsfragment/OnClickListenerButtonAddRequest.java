// Created on 03.03.2021, 21:10

package de.malik.myapplication.listeners.onclick.requestsfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.RequestFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Request;

public class OnClickListenerButtonAddRequest implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerButtonAddRequest(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        long id = system.getProjectManager().getNextRequestId();
        Request request = new Request(id, "Unbekannt " + id, RSKSystem.TimeManager.getCurrentDate(), "-");
        system.getProjectManager().getRequests().add(request);
        system.getFileManager().getPrinter().reprintFiles(system.getFileManager(), system.getProjectManager());
        system.replaceCurrentFragmentWith(new RequestFragment(system, request), R.anim.slide_up);
    }
}
