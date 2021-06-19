// Created on 06.03.2021, 13:46

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.requestfragment.OnClickListenerButtonConvertToCustomers;
import de.malik.myapplication.listeners.onclick.requestfragment.OnClickListenerButtonCancel;
import de.malik.myapplication.listeners.onclick.requestfragment.OnClickListenerButtonFinish;
import de.malik.myapplication.listeners.onclick.requestfragment.OnClickListenerButtonSetDate;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Request;

public class RequestFragment extends Fragment {

    private RSKSystem system;
    private View v;
    private EditText editTextName, editTextDate, editTextWorkDescription;
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
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = df.parse(request.getDate());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        c.setTime(date);
        editTextDate.setText(request.getDate() + " (" + RSKSystem.TimeManager.WEEK_DAYS[c.get(Calendar.DAY_OF_WEEK)] + ")");
    }

    private void createComponents() {
        editTextName = v.findViewById(R.id.editTextName);
        editTextDate = v.findViewById(R.id.editTextDate);
        editTextWorkDescription = v.findViewById(R.id.editTextWorkDescription);
        buttonFinish = v.findViewById(R.id.buttonFinish);
        buttonSetDate = v.findViewById(R.id.buttonSetDate);
        buttonCancel = v.findViewById(R.id.buttonCancel);
        buttonAddToCustomers = v.findViewById(R.id.buttonAddToCustomers);
    }

    private void setListeners() {
        buttonFinish.setOnClickListener(new OnClickListenerButtonFinish(system, request, this));
        buttonSetDate.setOnClickListener(new OnClickListenerButtonSetDate(system, editTextDate, request));
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
