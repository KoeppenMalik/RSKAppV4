package de.malik.myapplication.listeners.onclick.editprojectfragment;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import de.malik.myapplication.util.DatePickerFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;

public class OnClickListenerButtonSetDateEditProjectFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private RSKSystem system;
    private EditText editTextDate;
    private Project project;

    public OnClickListenerButtonSetDateEditProjectFragment(RSKSystem system, EditText editTextDate, Project project) {
        this.system = system;
        this.editTextDate = editTextDate;
        this.project = project;
    }

    @Override
    public void onClick(View v) {
        DialogFragment dialogFragment = new DatePickerFragment(this);
        dialogFragment.show(system.getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;
        String dateString = createDateString(dayOfMonth, month, year);
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
        project.setDate(dateString);
    }

    private String createDateString(int dayOfMonth, int month, int year) {
        String dayString = dayOfMonth + "", monthString = month + "";
        if (dayOfMonth < 10) {
            dayString = "0" + dayOfMonth;
        }
        if (month < 10) {
            monthString = "0" + month;
        }
        return dayString + "." + monthString + "." + year;
    }
}