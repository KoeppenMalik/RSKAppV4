// Created on 05.02.2021, 12:17

package de.malik.myapplication.listeners.onclick.addpausefragment;

import android.view.View;

import java.util.Date;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.AddPauseFragment;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.Pause;
import de.malik.mylibrary.managers.TimeManager;

public class OnClickListenerButtonFinish implements View.OnClickListener {

    private RSKSystem system;
    private Project project;
    private AddPauseFragment addPauseFragment;

    public OnClickListenerButtonFinish(RSKSystem system, Project project, AddPauseFragment addPauseFragment) {
        this.system = system;
        this.project = project;
        this.addPauseFragment = addPauseFragment;
    }

    @Override
    public void onClick(View v) {
        String hoursStr = addPauseFragment.getEditTextHours().getText().toString();
        String minutesStr = addPauseFragment.getEditTextMinutes().getText().toString();
        double hours, minutes;

        if (hoursStr.equals("") || hoursStr.isEmpty() || minutesStr.equals("") || minutesStr.isEmpty()) {
            system.makeShortToast("Stunden und Minuten müssen eingetragen werden");
            return;
        }
        hours = Integer.parseInt(hoursStr);
        minutes = Integer.parseInt(minutesStr);
        if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60) {
            if (hours == 0 && minutes == 0) {
                system.makeShortToast("Stunden und Minuten sind null");
            }
            else {
                Pause newPause = new Pause(system.getProjectManager().getNextPauseId(), new Date((long) TimeManager.hoursToMillis(hours + minutes / 60)));
                project.getPauses().add(newPause);
                system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
                system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_down);
            }
        }
        else system.makeShortToast("Falsche Eingabe");
    }
}
