// Created on 06.03.2021, 13:46

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import java.text.ParseException;

import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.editprojectfragment.OnClickListenerImageButtonChooseName;
import de.malik.myapplication.listeners.onclick.editprojectfragment.OnClickListenerImageButtonChooseWorkDescription;
import de.malik.myapplication.listeners.onclick.requestfragment.OnClickListenerButtonConvertToCustomers;
import de.malik.myapplication.listeners.onclick.requestfragment.OnClickListenerButtonCancel;
import de.malik.myapplication.listeners.onclick.requestfragment.OnClickListenerButtonFinish;
import de.malik.myapplication.listeners.onclick.requestfragment.OnClickListenerButtonSetDate;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Request;
import de.malik.mylibrary.managers.TimeManager;

public class RequestFragment extends Fragment {

    private RSKSystem system;
    private View v;
    private EditText editTextName, editTextDate, editTextWorkDescription;
    private ImageButton imageButtonSetRequestName, imageButtonSetRequestDescription;
    private Button buttonFinish, buttonSetDate, buttonCancel, buttonAddToCustomers;
    private Request request;

    public RequestFragment(RSKSystem system, Request request) {
        this.system = system;
        this.request = request;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.request_fragment, container, false);
        handleGui();
        return v;
    }

    private void handleGui() {
        createComponents();
        editTextName.setText(request.getName());
        editTextWorkDescription.setText(request.getDescription());
        setDateToTextView();
        setListeners();
    }

    private void setDateToTextView() {
        try {
            editTextDate.setText(request.getDate() + " (" + TimeManager.WEEK_DAYS[TimeManager.dayOfWeek(request.getDate()) -1] + ")");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void createComponents() {
        imageButtonSetRequestName = v.findViewById(R.id.imageButtonSetRequestName);
        imageButtonSetRequestDescription = v.findViewById(R.id.imageButtonSetRequestDescription);
        editTextName = v.findViewById(R.id.editTextName);
        editTextDate = v.findViewById(R.id.editTextDate);
        editTextWorkDescription = v.findViewById(R.id.editTextWorkDescription);
        buttonFinish = v.findViewById(R.id.buttonFinish);
        buttonSetDate = v.findViewById(R.id.buttonSetDate);
        buttonCancel = v.findViewById(R.id.buttonCancel);
        buttonAddToCustomers = v.findViewById(R.id.buttonAddToCustomers);
    }

    private void setListeners() {
        imageButtonSetRequestName.setOnClickListener(new OnClickListenerImageButtonChooseName(system, editTextName));
        imageButtonSetRequestDescription.setOnClickListener(new OnClickListenerImageButtonChooseWorkDescription(system, editTextWorkDescription));
        buttonFinish.setOnClickListener(new OnClickListenerButtonFinish(system, request, this));
        buttonSetDate.setOnClickListener(new OnClickListenerButtonSetDate(system, editTextDate));
        buttonCancel.setOnClickListener(new OnClickListenerButtonCancel(system));
        buttonAddToCustomers.setOnClickListener(new OnClickListenerButtonConvertToCustomers(system, request));
    }

    public EditText getEditTextName() {
        return editTextName;
    }

    public EditText getEditTextDate() {
        return editTextDate;
    }

    public EditText getEditTextWorkDescription() {
        return editTextWorkDescription;
    }
}
