package com.master_vision.trfihi.home.messages.viewmodel;

import android.content.Context;
import android.widget.Toast;

import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.methods.SharedPreferencesManager;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.home.messages._MessageService;
import com.master_vision.trfihi.home.messages.adapter.MessageAdapter;
import com.master_vision.trfihi.home.messages.model.MessageModel;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MessageViewModel {

    public MessageAdapter adapter;
    public ArrayList<MessageModel> dataList;

    private String userId;
    private _MessageService messageService;
    private Context context;

    public MessageViewModel(Context context) {
        this.context = context;
        this.userId = SharedPreferencesManager.getStringValue(Helper.USER_ID);
        this.messageService = NetworkClient.getService(messageService.getClass());
        this.adapter = new MessageAdapter(context);
        this.dataList = new ArrayList<MessageModel>();

        getAllUsersChat();
    }


    // APIS
    private void getAllUsersChat() {
        TrfihiApp.getCompositeDisposable().add(messageService.getAllUsersChat(userId)
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MessageModel>>() {
                    @Override
                    public void accept(List<MessageModel> response) {
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
