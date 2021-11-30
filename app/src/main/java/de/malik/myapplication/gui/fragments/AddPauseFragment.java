// Created on 04.02.2021, 22:36

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.Date;

import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.OnClickListenerButtonSave;
import de.malik.myapplication.listeners.onclick.OnClickListenerSwitchFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.Savable;
import de.malik.myapplication.util.projectmanagement.Pause;
import de.malik.myapplication.util.projectmanagement.Project;
import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.mylibrary.managers.TimeManager;

public class AddPauseFragment extends Fragment implements Savable {

    private RSKSystem system;
    private Project project;
    private View v;
    private Button buttonFinish, buttonCancel;
    private EditText editTextHours, editTextMinutes;

    public AddPauseFragment(Project project, RSKSystem system) {
        this.project = project;
        this.system = system;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.add_pause_fragment, container, false);
        handleGui();
        return v;
    }

    @Override
    public void performSave() {
        String hoursStr = editTextHours.getText().toString();
        String minutesStr = editTextMinutes.getText().toString();
        double hours, minutes;

        if (hoursStr.equals("") || hoursStr.isEmpty() || minutesStr.equals("") || minutesStr.isEmpty()) {
            system.makeToast("Stunden und Minuten müssen eingetragen werden");
            return;
        }
        hours = Integer.parseInt(hoursStr);
        minutes = Integer.parseInt(minutesStr);
        if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60) {
            if (hours == 0 && minutes == 0) {
                system.makeToast("Stunden und Minuten sind null");
            }
            else {
                ProjectManager projectManager = system.getProjectManager();
                Pause newPause = new Pause(projectManager.getNextId(projectManager.getPauses()), new Date((long) TimeManager.hoursToMillis(hours + minutes / 60)));
                project.getPauses().add(newPause);
                system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_down);
            }
        }
        else system.makeToast("Falsche Eingabe");
    }

    private void handleGui() {
        createComponents();
        editTextHours.setText("0");
        editTextMinutes.setText("0");
        setListeners();
    }

    private void createComponents() {
        buttonFinish = v.findViewById(R.id.buttonFinish);
        buttonCancel = v.findViewById(R.id.buttonCancel);
        editTextHours = v.findViewById(R.id.editTextHours);
        editTextMinutes = v.findViewById(R.id.editTextMinutes);
    }

    private void setListeners() {
        buttonFinish.setOnClickListener(new OnClickListenerButtonSave(this, system));
        buttonCancel.setOnClickListener(new OnClickListenerSwitchFragment(new ProjectFragment(system, project), system, R.anim.slide_down));
    }
}
