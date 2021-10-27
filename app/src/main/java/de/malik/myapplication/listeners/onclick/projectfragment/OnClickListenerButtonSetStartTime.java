// Created on 01.02.2021, 09:40

package de.malik.myapplication.listeners.onclick.projectfragment;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.TimePicker;
import androidx.fragment.app.DialogFragment;

import java.util.Date;

import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Pause;
import de.malik.myapplication.util.TimePickerFragment;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.TimeManager;

public class OnClickListenerButtonSetStartTime implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private RSKSystem system;
    private Project project;
    private ProjectFragment projectFragment;

    public OnClickListenerButtonSetStartTime(RSKSystem system, Project project, ProjectFragment projectFragment) {
        this.system = system;
        this.project = project;
        this.projectFragment = projectFragment;
    }

    @Override
    public void onClick(View v) {
        DialogFragment timePicker = new TimePickerFragment(this);
        timePicker.show(system.getSupportFragmentManager(), "time picker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String timeString = hourOfDay + TimeManager.default_time_separator + minute;
        Date selectedTime = new Date(TimeManager.toMillis(timeString));

        project.setStartTime(selectedTime);
        projectFragment.getEditTextStartTime().setText(TimeManager.formatTimeString(TimeManager.toTimeString(selectedTime, false)));
        setTotalTime();
        system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
    }

    private void setTotalTime() {
        String[] timeStrings = TimeManager.cutOffTimeSuffix(projectFragment.getEditTextStartTime().getText().toString(), projectFragment.getEditTextStopTime().getText().toString());
        Date startTime = new Date(TimeManager.toMillis(timeStrings[0]));
        Date stopTime = new Date(TimeManager.toMillis(timeStrings[1]));
        long diff = TimeManager.diff(stopTime, startTime);
        for (Pause pause : project.getPauses()) {
            diff -= pause.getTime().getTime();
        }
        projectFragment.getEditTextTotalTime().setText(TimeManager.toTimeString(new Date(diff), false));
    }
}
