package com.master_vision.trfihi.chat.model;

import com.google.gson.annotations.SerializedName;

public class ChatRequestParamModel {

    @SerializedName("SenderUserId")private String SenderUserId;
    @SerializedName("ReciverUserId")private String ReciverUserId;

    public ChatRequestParamModel(String senderUserId, String reciverUserId) {
        SenderUserId = senderUserId;
        ReciverUserId = reciverUserId;
    }

    public String getSenderUserId() {
        return SenderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        SenderUserId = senderUserId;
    }

    public String getReciverUserId() {
        return ReciverUserId;
    }

    public void setReciverUserId(String reciverUserId) {
        ReciverUserId = reciverUserId;
    }
}
