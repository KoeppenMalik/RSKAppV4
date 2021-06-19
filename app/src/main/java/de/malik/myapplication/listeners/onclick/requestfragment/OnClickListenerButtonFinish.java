// Created on 06.03.2021, 14:03

package de.malik.myapplication.listeners.onclick.requestfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.RequestFragment;
import de.malik.myapplication.gui.fragments.RequestsFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Request;

public class OnClickListenerButtonFinish implements View.OnClickListener {

    private RSKSystem system;
    private Request request;
    private RequestFragment requestFragment;

    public OnClickListenerButtonFinish(RSKSystem system, Request request, RequestFragment requestFragment) {
        this.system = system;
        this.request = request;
        this.requestFragment = requestFragment;
    }

    @Override
    public void onClick(View v) {
        request.setName(requestFragment.getEditTextName().getText().toString());
        String cleanDate = cleanDate();
        request.setDate(cleanDate);
        request.setDescription(requestFragment.getEditTextWorkDescription().getText().toString());
        system.getFileManager().getPrinter().reprintFiles(system.getFileManager(), system.getProjectManager());
        system.replaceCurrentFragmentWith(new RequestsFragment(system), R.anim.slide_down);
    }

    private String cleanDate() {
        String text = requestFragment.getEditTextDate().getText().toString();
        return text.split(" ")[0];
    }
}
