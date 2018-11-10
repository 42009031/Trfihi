package com.master_vision.trfihi.create_event.view_model;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateEventViewModel {

    public ObservableField<String> gameName = new ObservableField<>();
    public ObservableField<String> playerNo = new ObservableField<>();
    public ObservableField<String> fromDate = new ObservableField<>();
    public ObservableField<String> fromTime = new ObservableField<>();
    public ObservableField<String> toDate = new ObservableField<>();
    public ObservableField<String> toTime = new ObservableField<>();

    public ObservableField<Boolean> isLocationCurrentCheck = new ObservableField<>(false);
    public ObservableField<Boolean> isLocationOtherChecked = new ObservableField<>(false);
    public ObservableField<String> locationET = new ObservableField<>();
    public ObservableField<Integer> isShowLocationOther = new ObservableField<>(View.GONE);

    private Context context;

    // constructor
    public CreateEventViewModel(Context context) {
        this.context = context;
        requestPermission();
    }

    // buttons onclick listener
    public void onLocationCurrentClick(View view) {
        if (isLocationCurrentCheck.get()) {
            isLocationCurrentCheck.set(false);
        } else {
            isLocationCurrentCheck.set(true);
            isLocationOtherChecked.set(false);
            isShowLocationOther.set(View.GONE);
            locationET.set("");
        }
    }

    public void onLocationOtherClick(View view) {
        if (isLocationOtherChecked.get()) {
            isLocationOtherChecked.set(false);
            isShowLocationOther.set(View.GONE);
            locationET.set("");
        } else {
            isLocationCurrentCheck.set(false);
            isLocationOtherChecked.set(true);
            isShowLocationOther.set(View.VISIBLE);

        }
    }

    public void onFromTimeClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        displayTimePickerDialog(fromTime);
    }

    public void onFromDateClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        displayDatePickerDialog(fromDate);
    }

    public void onToTimeClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        displayTimePickerDialog(toTime);
    }

    public void onToDateClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        displayDatePickerDialog(toDate);
    }

    public void onCreateClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        if (TextUtils.isEmpty(gameName.get())) {
            printToast("Please enter game name.");
            return;
        }
        if (TextUtils.isEmpty(playerNo.get())) {
            printToast("Please enter required players number.");
            return;
        }
        if (TextUtils.isEmpty(fromDate.get())) {
            printToast("Please enter game start date.");
            return;
        }
        if (TextUtils.isEmpty(fromTime.get())) {
            printToast("Please enter game start time.");
            return;
        }
        if (TextUtils.isEmpty(toDate.get())) {
            printToast("Please enter game finish date.");
            return;
        }
        if (TextUtils.isEmpty(toTime.get())) {
            printToast("Please enter game finish time.");
            return;
        }
        if (isLocationCurrentCheck.get() == false && isLocationOtherChecked.get() == false) {
            printToast("Please select game location.");
            return;
        }
        if (isLocationOtherChecked.get() && TextUtils.isEmpty(locationET.get())) {
            printToast("Please enter game location.");
            return;
        }

        if(!compareTwoDate()){
            Toast.makeText(context, "Game finish date must Greater than game start date", Toast.LENGTH_LONG).show();
        }



        if (isLocationCurrentCheck.get()) {
            if ((ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) != PackageManager.PERMISSION_GRANTED) {
                requestPermission();
                return;
            }
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
        }


    }

    private boolean compareTwoDate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date fDate = formatter.parse(fromDate.get());
            Date tDate = formatter.parse(toDate.get());
            if (fDate.compareTo(tDate) < 0) {
                return true;
            }else{
                return false;
            }

        } catch (ParseException e1) {
            e1.printStackTrace();
            return false;
        }
    }


    // helper methods
    private void printToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    // dialogs
    private void displayDatePickerDialog(final ObservableField<String> observableField) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, AlertDialog.THEME_HOLO_DARK,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear++;
                        String day = String.valueOf(dayOfMonth), month = String.valueOf(monthOfYear);

                        if (monthOfYear < 10) {
                            month = "0" + monthOfYear;
                        }
                        if (dayOfMonth < 10) {
                            day = "0" + dayOfMonth;
                        }
                        observableField.set(day + "/" + month + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void displayTimePickerDialog(final ObservableField<String> observableField) {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String AM_PM = " AM";
                String mm_precede = "";
                if (hourOfDay >= 12) {
                    AM_PM = " PM";
                    if (hourOfDay >= 13 && hourOfDay < 24) {
                        hourOfDay -= 12;
                    } else {
                        hourOfDay = 12;
                    }
                } else if (hourOfDay == 0) {
                    hourOfDay = 12;
                }
                if (minute < 10) {
                    mm_precede = "0";
                }
                observableField.set(hourOfDay + ":" + mm_precede + minute + AM_PM);
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    // APIS


}
