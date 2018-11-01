package com.master_vision.trfihi.registration.register;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.databinding.ActivityRegistrationBinding;


public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;
    private RegistrationViewModel regVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
    }

    private void bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        regVM = new RegistrationViewModel(this);
        binding.setRegVM(regVM);
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
