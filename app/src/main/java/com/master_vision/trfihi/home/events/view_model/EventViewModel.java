package com.master_vision.trfihi.home.events.view_model;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.create_event.CreateEventActivity;
import com.master_vision.trfihi.home.events.adapter.EventAdapter;
import com.master_vision.trfihi.home.events.model.EventModel;

import java.util.ArrayList;

public class EventViewModel {

    public EventAdapter adapter;
    public ArrayList<EventModel> dataList;

//    private Activity activity;
    public EventViewModel() {
        adapter = new EventAdapter();

        dataList = new ArrayList<EventModel>() {{
            for (int i = 0; i < 5; i++) {
                add(new EventModel("01/01/2019 12:00 PM",
                        "1.0000",
                        "0.1111",
                        ""+i,
                        "Game title",
                        "0",
                        "Naser city",
                        "01/01/2019 12:00 PM",
                        "3",
                        "true",
                        "true",
                        "http://riptoned.com/wp-content/uploads/2017/01/no-user-image.png"));
            }
        }};
    }

    public void onCreateNewEventClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        TrfihiApp.context.startActivity(new Intent(TrfihiApp.context, CreateEventActivity.class));
    }
}
