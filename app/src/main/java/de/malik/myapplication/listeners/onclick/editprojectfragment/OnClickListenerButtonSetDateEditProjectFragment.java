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
import de.malik.mylibrary.managers.TimeManager;

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
        String dateString = TimeManager.formatDate(dayOfMonth, month +1, year);
        try {
            editTextDate.setText(dateString + " (" + TimeManager.WEEK_DAYS[TimeManager.dayOfWeek(dateString) -1] + ")");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        project.setDate(dateString);
    }
}