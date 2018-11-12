package com.master_vision.trfihi.chat.view_model;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.chat._ChatService;
import com.master_vision.trfihi.chat.adapter.ChatAdapter;
import com.master_vision.trfihi.chat.model.ChatModel;
import com.master_vision.trfihi.chat.model.ChatRequestParamModel;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.methods.SharedPreferencesManager;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.home.notification.model.NotificationModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class ChatViewModel {

    public ObservableField<String> message = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> profileImageUrl = new ObservableField<>();
    public ObservableField<Integer> scrollPosition = new ObservableField<>();
    public ChatAdapter adapter;
    public ArrayList<ChatModel> dataList;

    private Context context;
    private String userId, receiverUserId;
    private _ChatService chatService;

    // constructor
    public ChatViewModel(Context context, String receiverUserId){
        this.context = context;
        this.name.set(SharedPreferencesManager.getStringValue(Helper.DISPLAY_NAME));
        this.profileImageUrl.set(SharedPreferencesManager.getStringValue(Helper.PROFILE_IMAGE));
        this.adapter = new ChatAdapter(context);
        this.dataList = new ArrayList<ChatModel>();
        scrollPosition.set(dataList.size()-1);
        this.userId = SharedPreferencesManager.getStringValue(Helper.USER_ID);
        this.receiverUserId = receiverUserId;
        this.chatService = NetworkClient.getService(chatService.getClass());
        getSpecificChat();
    }


    // buttons onclick listener
    public void onSendClick(View view){
        view.startAnimation(Helper.BtnClickAnimation);
        if(!message.get().isEmpty()){

            addChat(new ChatModel(receiverUserId,
                    userId,
                    message.get(),
                    ""+new Date(),
                    "false"));


        }
    }

    public void onVideoClick(View view){
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onCallClick(View view){
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onAttachmentClick(View view){
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onStartEditing(View view){
        scrollPosition.set(dataList.size() - 1);
    }

    // APIS
    private void getSpecificChat() {
        TrfihiApp.getCompositeDisposable().add(chatService.getSpecificChat(new ChatRequestParamModel(userId, receiverUserId))
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ChatModel>>() {
                    @Override
                    public void accept(List<ChatModel> response) {
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

    private void addChat(final ChatModel model) {
        TrfihiApp.getCompositeDisposable().add(chatService.addChat(model)
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) {
                        Helper.dismissLoading();
                        if (response != null) {
                            if(response.equalsIgnoreCase("success")){
                                dataList.add(model);
                                int newMsgPosition = dataList.size() - 1;
                                adapter.notifyItemInserted(newMsgPosition);
                                scrollPosition.set(newMsgPosition);
                                message.set("");
                            }
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

    //Helper methods
    private void printToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
