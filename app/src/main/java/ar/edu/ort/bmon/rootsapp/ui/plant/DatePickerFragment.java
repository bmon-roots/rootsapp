package ar.edu.ort.bmon.rootsapp.ui.plant;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;
    private static boolean hasFutureDates;

    public static DatePickerFragment newInstance(boolean allowFutureDates, DatePickerDialog.OnDateSetListener listener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);
        hasFutureDates = allowFutureDates;
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), listener, year, month, day);

        c.set(Calendar.YEAR, year - 50);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        if (!hasFutureDates) {
            c.set(Calendar.YEAR, year);
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        }
        return datePickerDialog;
    }

}
