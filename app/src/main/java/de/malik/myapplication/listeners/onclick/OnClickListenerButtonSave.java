package de.malik.myapplication.listeners.onclick;

import android.view.View;

import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.Savable;

public class OnClickListenerButtonSave implements View.OnClickListener {

    private Savable savable;
    private RSKSystem system;

    public OnClickListenerButtonSave(Savable savable, RSKSystem system) {
        this.savable = savable;
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        savable.performSave();
        system.saveData();
    }
}
