package com.master_vision.trfihi.home.messages.model;

import com.google.gson.annotations.SerializedName;

public class MessageModel {

    @SerializedName("UserId")
    private String UserId;
    @SerializedName("DisplayName")
    private String DisplayName;
    @SerializedName("ImageProfile")
    private String ImageProfile;
    @SerializedName("UnseenMessageCount")
    private String UnseenMessageCount;
    @SerializedName("LastMessage")
    private String LastMessage;

    public MessageModel(String userId, String displayName, String imageProfile, String unseenMessageCount, String lastMessage) {
        UserId = userId;
        DisplayName = displayName;
        ImageProfile = imageProfile;
        UnseenMessageCount = unseenMessageCount;
        LastMessage = lastMessage;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getImageProfile() {
        return ImageProfile;
    }

    public void setImageProfile(String imageProfile) {
        ImageProfile = imageProfile;
    }

    public String getUnseenMessageCount() {
        return UnseenMessageCount;
    }

    public void setUnseenMessageCount(String unseenMessageCount) {
        UnseenMessageCount = unseenMessageCount;
    }

    public String getLastMessage() {
        return LastMessage;
    }

    public void setLastMessage(String lastMessage) {
        LastMessage = lastMessage;
    }
}
