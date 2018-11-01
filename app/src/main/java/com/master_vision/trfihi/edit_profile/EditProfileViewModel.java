package com.master_vision.trfihi.edit_profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;

public class EditProfileViewModel {

    public ObservableField<String> profileImageUrl = new ObservableField<>("http://riptoned.com/wp-content/uploads/2017/01/no-user-image.png");
    public ObservableBoolean maleCheck = new ObservableBoolean(true);
    public ObservableBoolean femaleCheck = new ObservableBoolean(false);

    public ObservableBoolean showImg = new ObservableBoolean(false);
    public ObservableBoolean showGender = new ObservableBoolean(false);
    public ObservableBoolean showDOF = new ObservableBoolean(false);


    public void onGalleryClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onCameraClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onSaveClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

        Toast.makeText(TrfihiApp.context, "image == " + showImg.get() +
                "\n Gender == " + showGender.get() +
                "\n DOB == " + showDOF.get() +
                "\n male == " + maleCheck.get(), Toast.LENGTH_LONG).show();
    }

    public void onMaleClick(View view) {
        maleCheck.set(true);
        femaleCheck.set(false);
    }

    public void onFemaleClick(View view) {
        maleCheck.set(false);
        femaleCheck.set(true);
    }

    public void onShowImgClick(View view) {
        showImg.set(showImg.get() ? false : true);
    }

    public void onShowGenderClick(View view) {
        showGender.set(showGender.get() ? false : true);
    }

    public void onShowDOFClick(View view) {
        showDOF.set(showDOF.get() ? false : true);
    }

    public void getDateChanged(DatePicker view, int year, int month, int day) {
        Toast.makeText(view.getContext(), day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
    }


}
