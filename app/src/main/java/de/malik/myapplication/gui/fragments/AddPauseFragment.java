// Created on 04.02.2021, 22:36

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.addpausefragment.OnClickListenerButtonCancel;
import de.malik.myapplication.listeners.onclick.addpausefragment.OnClickListenerButtonFinish;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;

public class AddPauseFragment extends Fragment {

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
        buttonFinish.setOnClickListener(new OnClickListenerButtonFinish(system, project, this));
        buttonCancel.setOnClickListener(new OnClickListenerButtonCancel(system, project));
    }

    public EditText getEditTextHours() {
        return editTextHours;
    }

    public EditText getEditTextMinutes() {
        return editTextMinutes;
    }
}
