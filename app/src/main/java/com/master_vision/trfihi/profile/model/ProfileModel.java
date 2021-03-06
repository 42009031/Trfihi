package com.master_vision.trfihi.profile.model;

import com.google.gson.annotations.SerializedName;

public class ProfileModel {

    @SerializedName("Id")
    private String Id;

    @SerializedName("DisplayName")
    private String DisplayName;

    @SerializedName("PhoneNumber")
    private String PhoneNumber;

    @SerializedName("Email")
    private String Email;

    @SerializedName("ProfileImageURL")
    private String ProfileImageURL;

    @SerializedName("VerficationCode")
    private String VerficationCode;

    @SerializedName("gender")
    private String gender;

    @SerializedName("DOB")
    private String DOB;

    public ProfileModel(String id, String displayName, String phoneNumber, String email, String profileImageURL, String verficationCode, String gender, String DOB) {
        Id = id;
        DisplayName = displayName;
        PhoneNumber = phoneNumber;
        Email = email;
        ProfileImageURL = profileImageURL;
        VerficationCode = verficationCode;
        this.gender = gender;
        this.DOB = DOB;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProfileImageURL() {
        return ProfileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        ProfileImageURL = profileImageURL;
    }

    public String getVerficationCode() {
        return VerficationCode;
    }

    public void setVerficationCode(String verficationCode) {
        VerficationCode = verficationCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
