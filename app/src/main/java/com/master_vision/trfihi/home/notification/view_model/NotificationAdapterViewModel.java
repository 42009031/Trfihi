package com.master_vision.trfihi.home.notification.view_model;

import android.databinding.ObservableField;
import android.view.View;


import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.home.notification.model.NotificationModel;

public class NotificationAdapterViewModel {

    public ObservableField<String> profileImageUrl = new ObservableField<>();
    public ObservableField<String> displayName = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();

    private NotificationModel model;

    public NotificationAdapterViewModel(NotificationModel model) {
        this.model = model;
        this.profileImageUrl.set(model.getProfileImageURL());
        this.displayName.set(model.getDisplayName());
        this.content.set(model.getNotificationMsg_ar());
        this.content.set(model.getNotificationMsg_en());
        this.date.set(model.getNotificationDate());
    }

    public void onNotificationItemClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }
}
