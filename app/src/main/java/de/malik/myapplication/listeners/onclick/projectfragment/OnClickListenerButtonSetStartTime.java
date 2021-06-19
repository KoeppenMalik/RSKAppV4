// Created on 01.02.2021, 09:40

package de.malik.myapplication.listeners.onclick.projectfragment;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.TimePicker;
import androidx.fragment.app.DialogFragment;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Time;
import de.malik.myapplication.util.TimePickerFragment;
import de.malik.myapplication.util.customermanagement.Project;

import static de.malik.myapplication.util.RSKSystem.TimeManager.*;

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
        String timeString = hourOfDay + ":" + minute + " Uhr";

        if (minute < 10) {
            timeString = hourOfDay + ":0" + minute + " Uhr";
        }
        project.setStartTime(RSKSystem.TimeManager.parseTime(timeString));
        projectFragment.getEditTextStartTime().setText(timeString);
        setTotalTime();
        system.getFileManager().getPrinter().reprintFiles(system.getFileManager(), system.getProjectManager());
    }

    private void setTotalTime() {
        String[] times = parseTimes(projectFragment.getEditTextStartTime().getText().toString(), projectFragment.getEditTextStopTime().getText().toString());
        Time startTime = parseTime(times[0]);
        Time stopTime = parseTime(times[1]);
        Time diff = getDiffInMinutes(startTime, stopTime, project.getPauses());

        projectFragment.getEditTextTotalTime().setText(diff.asDiffString());
    }
}
