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
import de.malik.myapplication.listeners.onclick.OnClickListenerButtonSave;
import de.malik.myapplication.listeners.onclick.OnClickListenerImageButtonChooseSavedCustomerData;
import de.malik.myapplication.listeners.onclick.OnClickListenerSwitchFragment;
import de.malik.myapplication.listeners.onclick.requestfragment.OnClickListenerButtonConvertToCustomers;
import de.malik.myapplication.listeners.onclick.requestfragment.OnClickListenerButtonSetDate;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.Savable;
import de.malik.myapplication.util.projectmanagement.CustomerData;
import de.malik.myapplication.util.projectmanagement.Request;
import de.malik.mylibrary.managers.TimeManager;

public class RequestFragment extends Fragment implements Savable {

    private RSKSystem system;
    private View v;
    private EditText editTextName, editTextDate, editTextWorkDescription;
    private Button buttonFinish, buttonSetDate, buttonCancel, buttonAddToCustomers;
    private ImageButton imageButtonChooseName, imageButtonChooseDescription;
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

    @Override
    public void performSave() {
        request.setName(editTextName.getText().toString());
        String cleanDate = editTextDate.getText().toString().split(" ")[0];
        request.setDate(cleanDate);
        request.setDescription(editTextWorkDescription.getText().toString());
        system.replaceCurrentFragmentWith(new RequestsFragment(system), R.anim.slide_down);
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
        editTextName = v.findViewById(R.id.editTextName);
        editTextDate = v.findViewById(R.id.editTextDate);
        editTextWorkDescription = v.findViewById(R.id.editTextWorkDescription);
        buttonFinish = v.findViewById(R.id.buttonFinish);
        buttonSetDate = v.findViewById(R.id.buttonSetDate);
        buttonCancel = v.findViewById(R.id.buttonCancel);
        buttonAddToCustomers = v.findViewById(R.id.buttonAddToCustomers);
        imageButtonChooseName = v.findViewById(R.id.imageButtonChooseName);
        imageButtonChooseDescription = v.findViewById(R.id.imageButtonChooseWorkDescription);
    }

    private void setListeners() {
        imageButtonChooseName.setOnClickListener(new OnClickListenerImageButtonChooseSavedCustomerData(system, editTextName, CustomerData.CHOOSE_SAVED_NAMES));
        imageButtonChooseDescription.setOnClickListener(new OnClickListenerImageButtonChooseSavedCustomerData(system, editTextWorkDescription, CustomerData.CHOOSE_SAVED_WORK_DESCRIPTIONS));
        buttonFinish.setOnClickListener(new OnClickListenerButtonSave(this, system));
        buttonSetDate.setOnClickListener(new OnClickListenerButtonSetDate(system, editTextDate, request));
        buttonCancel.setOnClickListener(new OnClickListenerSwitchFragment(new RequestsFragment(system), system, R.anim.slide_down));
        buttonAddToCustomers.setOnClickListener(new OnClickListenerButtonConvertToCustomers(system, request));
    }

    public EditText getEditTextWorkDescription() {
        return editTextWorkDescription;
    }
}
