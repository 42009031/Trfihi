package com.master_vision.trfihi.login;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.home._main.HomeActivity;


public class LoginViewModel extends BaseObservable {

    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<Boolean> hidePassword = new ObservableField<>(true);
    public ObservableField<Integer> hidePassImg = new ObservableField<>(R.drawable.ic_visibility);


    public LoginViewModel() {

    }


    // buttons on click listener

    public void onShowPasswordClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        if (hidePassword.get()) {
            hidePassword.set(false);
            hidePassImg.set(R.drawable.ic_visibility_off);
            notifyChange();
        } else {
            hidePassword.set(true);
            hidePassImg.set(R.drawable.ic_visibility);
            notifyChange();
        }
    }

    public void onLoginClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        view.getContext().startActivity(new Intent(view.getContext(), HomeActivity.class));
    }

    public void onFacebookClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onGoogleClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onTwitterClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

    }
}
