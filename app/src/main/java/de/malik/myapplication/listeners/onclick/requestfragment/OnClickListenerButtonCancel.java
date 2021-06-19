// Created on 07.03.2021, 10:57

package de.malik.myapplication.listeners.onclick.requestfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.RequestsFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerButtonCancel implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerButtonCancel(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        system.replaceCurrentFragmentWith(new RequestsFragment(system), R.anim.slide_down);
    }
}
