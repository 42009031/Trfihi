package com.master_vision.trfihi.registration.verification;


import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.master_vision.trfihi.R;

import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.databinding.ActivityVerificationBinding;
import com.master_vision.trfihi.registration.register.model.RegistrationRequestModel;
import com.master_vision.trfihi.registration.register.model.RegistrationResponseModel;

public class VerificationActivity extends AppCompatActivity {

    private ActivityVerificationBinding binding;
    private VerificationViewModel verVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        bind();
    }

    private void bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verification);
        verVM = new VerificationViewModel(this, (RegistrationRequestModel) getIntent().getParcelableExtra("RegistrationModel"),
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
