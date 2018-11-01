package com.master_vision.trfihi.profile.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.databinding.ActivityProfileBinding;
import com.master_vision.trfihi.profile.viewModel.ProfileViewModel;


public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private ProfileViewModel profVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();

    }

    @Override
    protected void onResume() {
        super.onResume();
        profVM.setUp();
    }

    @Override
    protected void onPause() {
        super.onPause();
        profVM.tearDown();
    }

    private void bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        profVM = new ProfileViewModel();
        binding.setProfVM(profVM);
    }

    public void onBackClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        finish();
    }
}
