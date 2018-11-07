package com.master_vision.trfihi.home.notification.model;

public class NotificationModel {

    private String NotificationDate;
    private String NotificationId;
    private String NotificationMsg_ar;
    private String NotificationMsg_en;
    private String NotificationSource_ar;
    private String NotificationSource_en;
    private String Seen;
    private String ToUserId;
    private String ProfileImageURL;
    private String UserId;
    private String DisplayName;
    private String ColorText;
    private String GameEventId;

    public NotificationModel(String notificationDate, String notificationId, String notificationMsg_ar, String notificationMsg_en, String notificationSource_ar, String notificationSource_en, String seen, String toUserId, String profileImageURL, String userId, String displayName, String colorText, String gameEventId) {
        NotificationDate = notificationDate;
        NotificationId = notificationId;
        NotificationMsg_ar = notificationMsg_ar;
        NotificationMsg_en = notificationMsg_en;
        NotificationSource_ar = notificationSource_ar;
        NotificationSource_en = notificationSource_en;
        Seen = seen;
        ToUserId = toUserId;
        ProfileImageURL = profileImageURL;
        UserId = userId;
        DisplayName = displayName;
        ColorText = colorText;
        GameEventId = gameEventId;
    }

    public String getNotificationDate() {
        return NotificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        NotificationDate = notificationDate;
    }

    public String getNotificationId() {
        return NotificationId;
    }

    public void setNotificationId(String notificationId) {
        NotificationId = notificationId;
    }

    public String getNotificationMsg_ar() {
        return NotificationMsg_ar;
    }

    public void setNotificationMsg_ar(String notificationMsg_ar) {
        NotificationMsg_ar = notificationMsg_ar;
    }

    public String getNotificationMsg_en() {
        return NotificationMsg_en;
    }

    public void setNotificationMsg_en(String notificationMsg_en) {
        NotificationMsg_en = notificationMsg_en;
    }

    public String getNotificationSource_ar() {
        return NotificationSource_ar;
    }

    public void setNotificationSource_ar(String notificationSource_ar) {
        NotificationSource_ar = notificationSource_ar;
    }

    public String getNotificationSource_en() {
        return NotificationSource_en;
    }

    public void setNotificationSource_en(String notificationSource_en) {
        NotificationSource_en = notificationSource_en;
    }

    public String getSeen() {
        return Seen;
    }

    public void setSeen(String seen) {
        Seen = seen;
    }

    public String getToUserId() {
        return ToUserId;
    }

    public void setToUserId(String toUserId) {
        ToUserId = toUserId;
    }

    public String getProfileImageURL() {
        return ProfileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        ProfileImageURL = profileImageURL;
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

    public String getColorText() {
        return ColorText;
    }

    public void setColorText(String colorText) {
        ColorText = colorText;
    }

    public String getGameEventId() {
        return GameEventId;
    }

    public void setGameEventId(String gameEventId) {
        GameEventId = gameEventId;
    }
}
