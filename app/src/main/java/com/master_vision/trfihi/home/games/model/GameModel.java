package com.master_vision.trfihi.home.games.model;

public class GameModel {

    private String Evaluation;
    private String EndTime;
    private String EventLat;
    private String EventLong;
    private String GameEventId;
    private String GameTitle;
    private String InitiatorUser;
    private String Location;
    private String StartTime;
    private String UserName;
    private String DisplayName;
    private String ProfileImageURL;
    private String PhoneNumber;
    private String joinStatus;
    private String cancelStatus;


    public GameModel(String evaluation, String endTime, String eventLat, String eventLong, String gameEventId, String gameTitle, String initiatorUser, String location, String startTime, String userName, String displayName, String profileImageURL, String phoneNumber, String joinStatus, String cancelStatus) {
        Evaluation = evaluation;
        EndTime = endTime;
        EventLat = eventLat;
        EventLong = eventLong;
        GameEventId = gameEventId;
        GameTitle = gameTitle;
        InitiatorUser = initiatorUser;
        Location = location;
        StartTime = startTime;
        UserName = userName;
        DisplayName = displayName;
        ProfileImageURL = profileImageURL;
        PhoneNumber = phoneNumber;
        this.joinStatus = joinStatus;
        this.cancelStatus = cancelStatus;
    }

    public String getEvaluation() {
        return Evaluation;
    }

    public void setEvaluation(String evaluation) {
        Evaluation = evaluation;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getEventLat() {
        return EventLat;
    }

    public void setEventLat(String eventLat) {
        EventLat = eventLat;
    }

    public String getEventLong() {
        return EventLong;
    }

    public void setEventLong(String eventLong) {
        EventLong = eventLong;
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

    public String getInitiatorUser() {
        return InitiatorUser;
    }

    public void setInitiatorUser(String initiatorUser) {
        InitiatorUser = initiatorUser;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(String joinStatus) {
        this.joinStatus = joinStatus;
    }

    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

}
