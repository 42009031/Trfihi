package com.master_vision.trfihi.create_event.controller;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.master_vision.trfihi.create_event._CreateEventInterface;

import java.util.Calendar;

public class CreateEventController implements _CreateEventInterface {

    @Override
    public String onDisplayFromDateDialog(Context context) {

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(((Activity)context).getFragmentManager(), "datePicker");

        return null;
    }

    @Override
    public String onDisplayFromTimeDialog(Context context) {
        return null;
    }

    @Override
    public String onDisplayToDateDialog(Context context) {
        return null;
    }

    @Override
    public String onDisplayToTimeDialog(Context context) {
        return null;
    }

    @Override
    public void onCreateEvent() {

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return  dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
//            btnDate.setText(ConverterDate.ConvertDate(year, month + 1, day));
        }
    }
}
