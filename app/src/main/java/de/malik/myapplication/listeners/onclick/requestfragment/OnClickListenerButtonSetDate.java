// Created on 06.03.2021, 14:23

package de.malik.myapplication.listeners.onclick.requestfragment;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.fragment.app.DialogFragment;

import java.text.ParseException;

import de.malik.myapplication.util.DatePickerFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.Request;
import de.malik.mylibrary.managers.TimeManager;

public class OnClickListenerButtonSetDate implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private RSKSystem system;
    private EditText editTextDate;
    private Request request;

    public OnClickListenerButtonSetDate(RSKSystem system, EditText editTextDate, Request request) {
        this.system = system;
        this.editTextDate = editTextDate;
        this.request = request;
    }

    @Override
    public void onClick(View v) {
        DialogFragment dialogFragment = new DatePickerFragment(this);
        dialogFragment.show(system.getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateString = TimeManager.formatDate(dayOfMonth, month +1, year);
        try {
            editTextDate.setText(dateString + " (" + TimeManager.WEEK_DAYS[TimeManager.dayOfWeek(dateString) - 1] + ")");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        system.saveData();
    }
}
