package com.master_vision.trfihi.home.join.model;

public class JoinModel {

 private String JoinTime;
    private String GameEventId;
    private String GameTitle;
    private String EndTime;
    private String StartTime;
    private String Location;
    private String EventLong;
    private String EventLat;
    private String PlayersCount;
    private String InitiatorUser;
    private String PhoneNumber;
    private String DisplayName;
    private String ProfileImageURL;
    private String cancelJoinStatus;
    private String reviewStatus;


    public JoinModel(String joinTime, String gameEventId, String gameTitle, String endTime, String startTime, String location, String eventLong, String eventLat, String playersCount, String initiatorUser, String phoneNumber, String displayName, String profileImageURL, String cancelJoinStatus, String reviewStatus) {
        JoinTime = joinTime;
        GameEventId = gameEventId;
        GameTitle = gameTitle;
        EndTime = endTime;
        StartTime = startTime;
        Location = location;
        EventLong = eventLong;
        EventLat = eventLat;
        PlayersCount = playersCount;
        InitiatorUser = initiatorUser;
        PhoneNumber = phoneNumber;
        DisplayName = displayName;
        ProfileImageURL = profileImageURL;
        this.cancelJoinStatus = cancelJoinStatus;
        this.reviewStatus = reviewStatus;
    }

    public String getJoinTime() {
        return JoinTime;
    }

    public void setJoinTime(String joinTime) {
        JoinTime = joinTime;
    }

    public String getGameEventId() {
        return GameEventId;
    }

    public void setGameEventId(String gameEventId) {
        GameEventId = gameEventId;
    }

    public String getGameTitle() {
        return GameTitle;
    }

    public void setGameTitle(String gameTitle) {
        GameTitle = gameTitle;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getEventLong() {
        return EventLong;
    }

    public void setEventLong(String eventLong) {
        EventLong = eventLong;
    }

    public String getEventLat() {
        return EventLat;
    }

    public void setEventLat(String eventLat) {
        EventLat = eventLat;
    }

    public String getPlayersCount() {
        return PlayersCount;
    }

    public void setPlayersCount(String playersCount) {
        PlayersCount = playersCount;
    }

    public String getInitiatorUser() {
        return InitiatorUser;
    }

    public void setInitiatorUser(String initiatorUser) {
        InitiatorUser = initiatorUser;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getProfileImageURL() {
        return ProfileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        ProfileImageURL = profileImageURL;
    }

    public String getCancelJoinStatus() {
        return cancelJoinStatus;
    }

    public void setCancelJoinStatus(String cancelJoinStatus) {
        this.cancelJoinStatus = cancelJoinStatus;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
}
