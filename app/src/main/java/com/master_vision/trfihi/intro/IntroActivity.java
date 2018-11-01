package com.master_vision.trfihi.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.login.LoginActivity;
import com.master_vision.trfihi.registration.register.RegistrationActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void loginAction(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        startActivity(new Intent(IntroActivity.this, LoginActivity.class));

    }

    public void signUpAction(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        startActivity(new Intent(IntroActivity.this, RegistrationActivity.class));

    }

}
