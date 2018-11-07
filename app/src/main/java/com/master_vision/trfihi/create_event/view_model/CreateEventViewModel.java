package com.master_vision.trfihi.create_event.view_model;

import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;

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
    }

    public void onFromDateClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }

    public void onToTimeClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        Toast.makeText(TrfihiApp.context, "text", Toast.LENGTH_SHORT).show();
    }

    public void onToDateClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        Toast.makeText(TrfihiApp.context, "text", Toast.LENGTH_SHORT).show();
    }

    public void onCreateClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }

}
