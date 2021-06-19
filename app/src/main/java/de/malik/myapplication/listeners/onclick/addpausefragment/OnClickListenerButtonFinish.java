// Created on 05.02.2021, 12:17

package de.malik.myapplication.listeners.onclick.addpausefragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.AddPauseFragment;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Time;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.Pause;

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
        int hours, minutes;

        if (hoursStr.isEmpty()) {
            hoursStr = "0";
        }
        else if (minutesStr.isEmpty()) {
            minutesStr = "0";
        }
        if (RSKSystem.TimeManager.isInteger(hoursStr) && RSKSystem.TimeManager.isInteger(minutesStr)) {
            hours = Integer.parseInt(hoursStr);
            minutes = Integer.parseInt(minutesStr);
            if (!(hours < 0) && !(minutes < 0) && (hours < 24 && minutes < 60)) {
                Pause newPause = new Pause(system.getProjectManager().getNextPauseId(), new Time(hours, minutes));
                project.getPauses().add(newPause);
                system.getFileManager().getPrinter().reprintFiles(system.getFileManager(), system.getProjectManager());
                system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_down);
            }
            else system.makeShortToast("Falsche Eingabe");
        }
        else system.makeShortToast("Bitte verwende Zahlen");
    }
}
