package com.master_vision.trfihi.home.events.view_model;

import android.databinding.ObservableField;
import android.view.View;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.home.events._EventCallBack;
import com.master_vision.trfihi.home.events.model.EventModel;

public class EventAdapterViewModel {

    public ObservableField<String> profileImageUrl = new ObservableField<>();
    public ObservableField<String> eventData = new ObservableField<>();
    private EventModel model;
    private _EventCallBack eventCallBack;

    public EventAdapterViewModel(EventModel model, _EventCallBack eventCallBack) {
        this.model = model;
        String eventDataStr = TrfihiApp.context.getString(R.string.event_data_template);
        eventDataStr = eventDataStr.replace("{game_name}", model.getGameTitle());
        eventDataStr = eventDataStr.replace("{player_no}", model.getInitiatorUser());
        eventDataStr = eventDataStr.replace("{from}", model.getStartTime());
        eventDataStr = eventDataStr.replace("{to}", model.getEndTime());
        eventDataStr = eventDataStr.replace("{city_name}", model.getLocation());
        eventData.set(eventDataStr);
        profileImageUrl.set(model.getProfileImageURL());
        this.eventCallBack = eventCallBack;
    }

    public void onDeleteEventClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        if (eventCallBack != null)
            eventCallBack.onDeleteEvent(model);
    }

    public void onJoinEventClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onEventClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }


}
