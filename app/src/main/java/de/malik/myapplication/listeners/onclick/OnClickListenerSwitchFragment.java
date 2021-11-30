package de.malik.myapplication.listeners.onclick;

import android.view.View;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.fragment.app.Fragment;

import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerSwitchFragment implements View.OnClickListener {

    private Fragment newFragment;
    private RSKSystem system;
    private int anim;

    public OnClickListenerSwitchFragment(Fragment newFragment, RSKSystem system, @AnimRes @AnimatorRes int anim) {
        this.newFragment = newFragment;
        this.system = system;
        this.anim = anim;
    }

    @Override
    public void onClick(View v) {
        system.replaceCurrentFragmentWith(newFragment, anim);
    }
}
