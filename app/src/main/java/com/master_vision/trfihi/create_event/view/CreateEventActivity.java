package com.master_vision.trfihi.create_event.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.create_event.view_model.CreateEventViewModel;
import com.master_vision.trfihi.databinding.ActivityCreateEventBinding;

public class CreateEventActivity extends AppCompatActivity {

    private ActivityCreateEventBinding binding;
    private CreateEventViewModel eventVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
    }

    private void bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_event);
        eventVM = new CreateEventViewModel(this);
        binding.setEventVM(eventVM);
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
