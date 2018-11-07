package com.master_vision.trfihi.registration.register.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RegistrationRequestModel implements Parcelable{

//    { "UserName":"EmailAddress@domain.com",
//            "DisplayName":"Super Man",
//            "Email":"EmailAddress@domain.com",
//            "PhoneNumber":"010101010100",
//            "Password":"Test123!" }

    @SerializedName("UserName")
    private String UserName;
    @SerializedName("DisplayName")
    private String DisplayName;
    @SerializedName("Email")
    private String Email;
    @SerializedName("PhoneNumber")
    private String PhoneNumber;
    @SerializedName("Password")
    private String Password;

    public RegistrationRequestModel(String userName, String displayName, String email, String phoneNumber, String password) {
        UserName = userName;
        DisplayName = displayName;
        Email = email;
        PhoneNumber = phoneNumber;
        Password = password;
    }

    protected RegistrationRequestModel(Parcel in) {
        UserName = in.readString();
        DisplayName = in.readString();
        Email = in.readString();
        PhoneNumber = in.readString();
        Password = in.readString();
    }

    public static final Creator<RegistrationRequestModel> CREATOR = new Creator<RegistrationRequestModel>() {
        @Override
        public RegistrationRequestModel createFromParcel(Parcel in) {
            return new RegistrationRequestModel(in);
        }

        @Override
        public RegistrationRequestModel[] newArray(int size) {
            return new RegistrationRequestModel[size];
        }
    };

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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(UserName);
        parcel.writeString(DisplayName);
        parcel.writeString(Email);
        parcel.writeString(PhoneNumber);
        parcel.writeString(Password);
    }
}
