// Created on 26.01.2021, 19:17

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import java.text.ParseException;

import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.OnClickListenerButtonSave;
import de.malik.myapplication.listeners.onclick.OnClickListenerSwitchFragment;
import de.malik.myapplication.listeners.onclick.editprojectfragment.OnClickListenerButtonConvertToRequest;
import de.malik.myapplication.listeners.onclick.editprojectfragment.OnClickListenerButtonSetDateEditProjectFragment;
import de.malik.myapplication.listeners.onclick.OnClickListenerImageButtonChooseSavedCustomerData;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.Savable;
import de.malik.myapplication.util.projectmanagement.CustomerData;
import de.malik.myapplication.util.projectmanagement.Project;
import de.malik.mylibrary.managers.TimeManager;

public class EditProjectDataFragment extends Fragment implements Savable {

    private RSKSystem system;
    private View v;
    private Project project;
    private TextView textViewCurrentName;
    private EditText editTextName, editTextDate, editTextWorkDescription;
    private Button buttonFinish, buttonCancel, buttonConvertToRequest, buttonSetDate;
    private ImageButton imageButtonChooseName, imageButtonChooseWorkDescription;

    public EditProjectDataFragment(RSKSystem system, Project project) {
        this.system = system;
        this.project = project;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.edit_project_data_fragment, container, false);
        handleGui();
        return v;
    }

    @Override
    public void performSave() {
        String name, workDescription;

        if (!editTextName.getText().toString().equals("")) {
            name = editTextName.getText().toString();
        }
        else name = "-";
        if (!editTextWorkDescription.getText().toString().equals("")) {
            workDescription = editTextWorkDescription.getText().toString();
        }
        else workDescription = "-";
        textViewCurrentName.setText(name);
        project.setName(name);
        project.setWorkDescription(workDescription);
        system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_down);
    }

    private void handleGui() {
        String name = project.getName(), workDescription = project.getWorkDescription();

        createComponents();
        if (name.equals("")) {
            name = "-";
        }
        if (workDescription.equals("")) {
            workDescription = "-";
        }
        textViewCurrentName.setText(name);
        editTextName.setText(name);
        editTextWorkDescription.setText(workDescription);
        try {
            editTextDate.setText(project.getDate() + " (" + TimeManager.WEEK_DAYS[TimeManager.dayOfWeek(project.getDate()) -1] + ")");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setListeners();
    }

    private void createComponents() {
        buttonSetDate = v.findViewById(R.id.buttonSetDate);
        editTextDate = v.findViewById(R.id.editTextDate);
        imageButtonChooseName = v.findViewById(R.id.imageButtonChooseName);
        textViewCurrentName = v.findViewById(R.id.textViewCurrentName);
        editTextName = v.findViewById(R.id.editTextName);
        editTextWorkDescription = v.findViewById(R.id.editTextWorkDescription);
        buttonFinish = v.findViewById(R.id.buttonFinish);
        buttonCancel = v.findViewById(R.id.buttonCancel);
        buttonConvertToRequest = v.findViewById(R.id.buttonConvertToRequest);
        imageButtonChooseWorkDescription = v.findViewById(R.id.imageButtonChooseWorkDescription);
    }

    private void setListeners() {
        buttonFinish.setOnClickListener(new OnClickListenerButtonSave(this, system));
        buttonCancel.setOnClickListener(new OnClickListenerSwitchFragment(new ProjectFragment(system, project), system, R.anim.slide_down));
        imageButtonChooseName.setOnClickListener(new OnClickListenerImageButtonChooseSavedCustomerData(system, editTextName, CustomerData.CHOOSE_SAVED_NAMES));
        imageButtonChooseWorkDescription.setOnClickListener(new OnClickListenerImageButtonChooseSavedCustomerData(system, editTextWorkDescription, CustomerData.CHOOSE_SAVED_WORK_DESCRIPTIONS));
        buttonConvertToRequest.setOnClickListener(new OnClickListenerButtonConvertToRequest(system, project));
        buttonSetDate.setOnClickListener(new OnClickListenerButtonSetDateEditProjectFragment(system, editTextDate, project));
    }

    public EditText getEditTextWorkDescription() {
        return editTextWorkDescription;
    }
}
