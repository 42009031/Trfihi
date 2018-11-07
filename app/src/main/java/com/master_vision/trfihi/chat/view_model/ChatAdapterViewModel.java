package com.master_vision.trfihi.chat.view_model;

import android.databinding.ObservableField;
import android.view.View;

import com.master_vision.trfihi.chat.model.ChatModel;

public class ChatAdapterViewModel {

    public ObservableField<String> senderMsg = new ObservableField<>();
    public ObservableField<Integer> isSender = new ObservableField<>(View.GONE);
    public ObservableField<String> receiverMsg = new ObservableField<>();
    public ObservableField<Integer> isReceiver = new ObservableField<>(View.GONE);
    public ObservableField<String> date = new ObservableField<>();

    private ChatModel model;

    public ChatAdapterViewModel(ChatModel model) {
        this.model = model;

        String msg = model.getMessage();
        if(model.getFromUserId().equals("1")){
            senderMsg.set(msg);
            isSender.set(View.VISIBLE);
        }else{
            receiverMsg.set(msg);
            isReceiver.set(View.VISIBLE);
        }
        date.set(model.getMessageDate());

    }
}
