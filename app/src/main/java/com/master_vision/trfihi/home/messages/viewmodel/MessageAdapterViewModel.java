package com.master_vision.trfihi.home.messages.viewmodel;

import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.master_vision.trfihi.chat.view.ChatActivity;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.home.messages.model.MessageModel;

public class MessageAdapterViewModel {

    public ObservableField<String> profileImageUrl = new ObservableField<>();
    public ObservableField<String> displayName = new ObservableField<>();
    public ObservableField<String> lastMessage = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();

    private MessageModel model;

    public MessageAdapterViewModel(MessageModel model) {
        this.model = model;
        this.profileImageUrl.set(model.getImageProfile());
        this.displayName.set(model.getDisplayName());
        this.lastMessage.set(model.getLastMessage());
        this.date.set("12:00 PM");
    }

    public void onChatItemClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        view.getContext().startActivity(new Intent(view.getContext(), ChatActivity.class));
    }
}
