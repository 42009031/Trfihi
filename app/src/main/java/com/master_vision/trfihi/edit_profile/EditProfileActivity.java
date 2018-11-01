package com.master_vision.trfihi.edit_profile;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.databinding.ActivityEditProfileBinding;
import com.master_vision.trfihi.login.LoginViewModel;

public class EditProfileActivity extends AppCompatActivity {

    private ActivityEditProfileBinding binding;
    private EditProfileViewModel e_prof_vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bind();
    }

    private void bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        e_prof_vm = new EditProfileViewModel();
        binding.setProfVM(e_prof_vm);
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
