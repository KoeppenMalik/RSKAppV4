// Created on 01.02.2021, 10:06

package de.malik.myapplication.util;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.TimeZone;

public class TimePickerFragment extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener listener;

    public TimePickerFragment(TimePickerDialog.OnTimeSetListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        int hour = c.get(Calendar.HOUR_OF_DAY);
        final int MINUTE = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), listener, hour, MINUTE, true);
    }
}
