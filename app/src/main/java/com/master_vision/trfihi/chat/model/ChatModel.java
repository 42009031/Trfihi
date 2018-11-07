package com.master_vision.trfihi.chat.model;

public class ChatModel {

//    "ToUserId": "c426ae73-0b6c-4f92-b0c5-bd571ac64881",
//            "FromUserId": "527d64cb-24d2-46e7-92ea-e031c2a96c9b",
//            "Message": "Hi",
//            "MessageDate": "2018-08-14T22:00:00",
//            "MessageId": 343,
//            "isSeen": true

    private String ToUserId;
    private String FromUserId;
    private String Message;
    private String MessageDate;
    private String MessageId;
    private String isSeen;


    public ChatModel(String toUserId, String fromUserId, String message, String messageDate, String messageId, String isSeen) {
        ToUserId = toUserId;
        FromUserId = fromUserId;
        Message = message;
        MessageDate = messageDate;
        MessageId = messageId;
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
