package com.master_vision.trfihi.registration.verification;

import android.databinding.ObservableField;
import android.view.View;

import com.master_vision.trfihi.common.methods.Helper;


public class VerificationViewModel {

    public ObservableField<String> code = new ObservableField<>();


    public void onSendVerificationCodeClick(View view){
        view.startAnimation(Helper.BtnClickAnimation);
    }

    public void onResendClick(View view){
        view.startAnimation(Helper.BtnClickAnimation);

    }
}
