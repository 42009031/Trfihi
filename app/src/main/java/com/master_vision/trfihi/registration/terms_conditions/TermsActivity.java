package com.master_vision.trfihi.registration.terms_conditions;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.databinding.ActivityTermsBinding;
import com.master_vision.trfihi.common.methods.Helper;

public class TermsActivity extends AppCompatActivity {

    ActivityTermsBinding binding;
    TermsViewModel termsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
    }

    private void bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_terms);
        termsVM = new TermsViewModel();
        binding.setTermsVM(termsVM);
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
