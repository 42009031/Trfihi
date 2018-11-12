package com.master_vision.trfihi.home.notification.view_model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.methods.SharedPreferencesManager;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.home._main.HomeActivity;
import com.master_vision.trfihi.home.notification._NotificationService;
import com.master_vision.trfihi.home.notification.adapter.NotificationAdapter;
import com.master_vision.trfihi.home.notification.model.NotificationModel;
import com.master_vision.trfihi.registration.register.model.TokenResponseModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class NotificationViewModel {
    public NotificationAdapter adapter;
    public ArrayList<NotificationModel> dataList;

    private Context context;
    private _NotificationService notificationService;

    public NotificationViewModel(Context context) {
        this.context = context;
        this.notificationService = NetworkClient.getService(notificationService.getClass());
        adapter = new NotificationAdapter(context);
        dataList = new ArrayList<NotificationModel>();
        getAllNotifications();
    }

    public void getAllNotifications() {
        String userId = SharedPreferencesManager.getStringValue(Helper.USER_ID);
        TrfihiApp.getCompositeDisposable().add(notificationService.getAllNotifications(userId)
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NotificationModel>>() {
                    @Override
                    public void accept(List<NotificationModel> response) {
                        Helper.dismissLoading();
                        if (response.size() != 0) {
                            dataList.addAll(response);
                            adapter.updateData(dataList);
                        } else {
                            printToast("Server response error !!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Helper.dismissLoading();
                        throwable.printStackTrace();
                        printToast(throwable.getMessage());
                    }
                }));
    }

    private void printToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
