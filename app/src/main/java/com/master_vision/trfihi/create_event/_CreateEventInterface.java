package com.master_vision.trfihi.create_event;

import android.content.Context;

public interface  _CreateEventInterface {

    String onDisplayFromDateDialog(Context context);
    String onDisplayFromTimeDialog(Context context);
    String onDisplayToDateDialog(Context context);
    String onDisplayToTimeDialog(Context context);
    void onCreateEvent();

}
