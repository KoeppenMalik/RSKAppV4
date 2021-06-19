// Created on 07.03.2021, 18:40

package de.malik.myapplication.util.recyclerviews.requests;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.RequestFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Request;

public class OnClickListenerViewHolderRequests implements View.OnClickListener {

    private RSKSystem system;
    private Request request;

    public OnClickListenerViewHolderRequests(RSKSystem system, Request request) {
        this.system = system;
        this.request = request;
    }

    @Override
    public void onClick(View v) {
        system.replaceCurrentFragmentWith(new RequestFragment(system, request), R.anim.slide_up);
    }
}
