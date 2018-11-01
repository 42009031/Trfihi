package com.master_vision.trfihi.home.events.model;

public class EventModel {

    private String EndTime;
    private String EventLat;
    private String EventLong;
    private String GameEventId;
    private String GameTitle;
    private String InitiatorUser;
    private String Location;
    private String StartTime;
    private String PlayersCount;
    private String cancelGamesStatus;
    private String editGameStatus;
    private String ProfileImageURL;


    public EventModel(String endTime, String eventLat, String eventLong, String gameEventId, String gameTitle, String initiatorUser, String location, String startTime, String playersCount, String cancelGamesStatus, String editGameStatus, String profileImageURL) {
        EndTime = endTime;
        EventLat = eventLat;
        EventLong = eventLong;
        GameEventId = gameEventId;
        GameTitle = gameTitle;
        InitiatorUser = initiatorUser;
        Location = location;
        StartTime = startTime;
        PlayersCount = playersCount;
        this.cancelGamesStatus = cancelGamesStatus;
        this.editGameStatus = editGameStatus;
        ProfileImageURL = profileImageURL;
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

    public String getPlayersCount() {
        return PlayersCount;
    }

    public void setPlayersCount(String playersCount) {
        PlayersCount = playersCount;
    }

    public String getCancelGamesStatus() {
        return cancelGamesStatus;
    }

    public void setCancelGamesStatus(String cancelGamesStatus) {
        this.cancelGamesStatus = cancelGamesStatus;
    }

    public String getEditGameStatus() {
        return editGameStatus;
    }

    public void setEditGameStatus(String editGameStatus) {
        this.editGameStatus = editGameStatus;
    }

    public String getProfileImageURL() {
        return ProfileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        ProfileImageURL = profileImageURL;
    }
}
