// Created on 02.03.2021, 19:17

package de.malik.myapplication.listeners.onclick.overviewfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.RequestsFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerViewRequests implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerViewRequests(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        system.replaceCurrentFragmentWith(new RequestsFragment(system), R.anim.slide_up);
    }
}
