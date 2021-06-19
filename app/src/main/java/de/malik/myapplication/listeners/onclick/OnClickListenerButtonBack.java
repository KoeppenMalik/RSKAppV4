// Created on 16.02.2021, 18:54

package de.malik.myapplication.listeners.onclick;

import android.view.View;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.OverviewFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerButtonBack implements View.OnClickListener {

    private RSKSystem system;
    private int anim;

    public OnClickListenerButtonBack(RSKSystem system, @AnimatorRes @AnimRes int anim) {
        this.system = system;
        this.anim = anim;
    }

    @Override
    public void onClick(View v) {
        system.replaceCurrentFragmentWith(new OverviewFragment(system), anim);
    }
}
