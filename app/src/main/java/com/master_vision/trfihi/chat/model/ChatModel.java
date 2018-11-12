package com.master_vision.trfihi.chat.model;

import com.google.gson.annotations.SerializedName;

public class ChatModel {

    @SerializedName("ToUserId")
    private String ToUserId;
    @SerializedName("FromUserId")
    private String FromUserId;
    @SerializedName("Message")
    private String Message;
    @SerializedName("MessageDate")
    private String MessageDate;
    @SerializedName("MessageId")
    private String MessageId;
    @SerializedName("isSeen")
    private String isSeen;


    public ChatModel(String toUserId, String fromUserId, String message, String messageDate, String messageId, String isSeen) {
        ToUserId = toUserId;
        FromUserId = fromUserId;
        Message = message;
        MessageDate = messageDate;
        MessageId = messageId;
        this.isSeen = isSeen;
    }

    public ChatModel(String toUserId, String fromUserId, String message, String messageDate, String isSeen) {
        ToUserId = toUserId;
        FromUserId = fromUserId;
        Message = message;
        MessageDate = messageDate;
        this.isSeen = isSeen;
    }

    public String getToUserId() {
        return ToUserId;
    }

    public void setToUserId(String toUserId) {
        ToUserId = toUserId;
    }

    public String getFromUserId() {
        return FromUserId;
    }

    public void setFromUserId(String fromUserId) {
        FromUserId = fromUserId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getMessageDate() {
        return MessageDate;
    }

    public void setMessageDate(String messageDate) {
        MessageDate = messageDate;
    }

    public String getMessageId() {
        return MessageId;
    }

    public void setMessageId(String messageId) {
        MessageId = messageId;
    }

    public String getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(String isSeen) {
        this.isSeen = isSeen;
    }
}
