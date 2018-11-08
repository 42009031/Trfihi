package com.master_vision.trfihi.registration.verification;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.master_vision.trfihi.R;

import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.databinding.ActivityVerificationBinding;
import com.master_vision.trfihi.registration.register.model.RegistrationRequestModel;
import com.master_vision.trfihi.registration.register.model.RegistrationResponseModel;

public class VerificationActivity extends AppCompatActivity {

    private ActivityVerificationBinding binding;
    private VerificationViewModel verVM;
    private RegistrationRequestModel regParamModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        bind();
    }

    private void bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verification);
        regParamModel = getIntent().getParcelableExtra("RegistrationModel");
        verVM = new VerificationViewModel(this,
                regParamModel,
                getIntent().getStringExtra("PhoneVerificationId"));
        binding.setVerifVM(verVM);
    }


    public void onBackClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
