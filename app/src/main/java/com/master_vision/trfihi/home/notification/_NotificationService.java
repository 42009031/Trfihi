package com.master_vision.trfihi.home.notification;

import com.master_vision.trfihi.home.notification.model.NotificationModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface _NotificationService {

    @GET("api/GetAllNotifications")
    Observable<List<NotificationModel>> getAllNotifications(@Body String userId);
}
