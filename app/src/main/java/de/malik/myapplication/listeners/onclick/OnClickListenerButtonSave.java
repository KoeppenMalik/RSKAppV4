// Created on 05.02.2021, 20:07

package de.malik.myapplication.listeners.onclick;

import android.view.View;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerButtonSave implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerButtonSave(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
        system.makeShortToast("Kunden gespeichert");
    }
}
