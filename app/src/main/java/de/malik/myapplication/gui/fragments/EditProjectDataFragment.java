// Created on 26.01.2021, 19:17

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.editprojectfragment.OnClickListenerButtonCancel;
import de.malik.myapplication.listeners.onclick.editprojectfragment.OnClickListenerButtonConvertToRequest;
import de.malik.myapplication.listeners.onclick.editprojectfragment.OnClickListenerButtonFinish;
import de.malik.myapplication.listeners.onclick.editprojectfragment.OnClickListenerButtonSetDateEditProjectFragment;
import de.malik.myapplication.listeners.onclick.editprojectfragment.OnClickListenerImageButtonChooseName;
import de.malik.myapplication.listeners.onclick.editprojectfragment.OnClickListenerImageButtonChooseWorkDescription;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.mylibrary.managers.TimeManager;

public class EditProjectDataFragment extends Fragment {

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
        buttonFinish.setOnClickListener(new OnClickListenerButtonFinish(system, project, this));
        buttonCancel.setOnClickListener(new OnClickListenerButtonCancel(system, project));
        imageButtonChooseName.setOnClickListener(new OnClickListenerImageButtonChooseName(system, editTextName));
        imageButtonChooseWorkDescription.setOnClickListener(new OnClickListenerImageButtonChooseWorkDescription(system, editTextWorkDescription));
        buttonConvertToRequest.setOnClickListener(new OnClickListenerButtonConvertToRequest(system, project));
        buttonSetDate.setOnClickListener(new OnClickListenerButtonSetDateEditProjectFragment(system, editTextDate, project));
    }

    public TextView getTextViewCurrentName() {
        return textViewCurrentName;
    }

    public EditText getEditTextName() {
        return editTextName;
    }

    public EditText getEditTextWorkDescription() {
        return editTextWorkDescription;
    }
}
