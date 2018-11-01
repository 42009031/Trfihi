package com.master_vision.trfihi.evaluation;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.databinding.ActivityEvaluationBinding;
import com.master_vision.trfihi.common.methods.Helper;

public class EvaluationActivity extends AppCompatActivity {

    ActivityEvaluationBinding binding;
    EvaluationViewModel evalVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
    }

    private void bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_evaluation);
        evalVM = new EvaluationViewModel();
        binding.setEvalVM(evalVM);

//binding

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
