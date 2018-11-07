package com.master_vision.trfihi.home.notification.view_model;

import android.content.Context;

import com.master_vision.trfihi.home.notification.adapter.NotificationAdapter;
import com.master_vision.trfihi.home.notification.model.NotificationModel;
import java.util.ArrayList;

public class NotificationViewModel {
    public NotificationAdapter adapter;
    public ArrayList<NotificationModel> dataList;

    public NotificationViewModel(Context context) {
        adapter = new NotificationAdapter(context);
        dataList = new ArrayList<NotificationModel>() {{
            for (int i = 0; i <= 5; i++) {
                add(new NotificationModel("01/01/2019 12:00PM",
                        "000",
                        "How are you man want to play with you hhhhhhhhhhhhhhhhhhhhh",
                        "How are you man want to play with you hhhhhhhhhhhhhhhhhhhhh",
                        "test test test test",
                        "test test test test",
                        "false",
                        "00",
                        "http://riptoned.com/wp-content/uploads/2017/01/no-user-image.png",
                        "00",
                        "My name",
                        "test",
                        "00"));
            } }
        };

    }
}
