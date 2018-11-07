package com.master_vision.trfihi.home.join.view_model;

import android.databinding.ObservableField;
import android.view.View;


import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.home.join.model.JoinModel;

public class JoinAdapterViewModel {

    public ObservableField<String> profileImageUrl = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();

    private JoinModel model;

    public JoinAdapterViewModel(JoinModel model) {
        this.model = model;
        this.profileImageUrl.set(model.getProfileImageURL());
//        this.content.set();
    }

    public void onJoinItemClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }

//    public void onJoinClick(View view) {
//        view.startAnimation(Helper.BtnClickAnimation);
//    }

    public void onCancelClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }
}
