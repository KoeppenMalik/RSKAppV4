// Created on 06.03.2021, 14:23

package de.malik.myapplication.listeners.onclick.requestfragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import de.malik.myapplication.util.DatePickerFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.TimePickerFragment;
import de.malik.myapplication.util.customermanagement.Request;

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
        month++;
        String dateString = createDateString(year, month, dayOfMonth);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = df.parse(dayOfMonth + "." + month + "." + year);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        c.setTime(date);
        editTextDate.setText(dateString + " (" + RSKSystem.TimeManager.WEEK_DAYS[c.get(Calendar.DAY_OF_WEEK)] + ")");
        system.getFileManager().getPrinter().reprintFiles(system.getFileManager(), system.getProjectManager());
    }

    private String createDateString(int year, int month, int dayOfMonth) {
        String monthString = month + "", dayString = dayOfMonth + "";
        if (month < 10) {
            monthString = "0" + month;
        }
        if (dayOfMonth < 10) {
            dayString = "0" + dayOfMonth;
        }
        return dayString + "." + monthString + "." + year;
    }
}
