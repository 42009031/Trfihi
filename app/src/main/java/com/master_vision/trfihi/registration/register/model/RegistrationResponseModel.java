package com.master_vision.trfihi.registration.register.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponseModel implements Parcelable{

//    {"DisplayName":"Ahmed Dawoud",
//            "VerficationCode":"199",
//            "Email":"amadoknight766@gmail.com",
//            "EmailConfirmed":false,
//            "PasswordHash":"APQphANaLLGUzyH8MuDFtymOjDjwzk8QbSSl/WyEwEP9t5Q41XTRyeRufa7BaUYyMQ==",
//            "SecurityStamp":"b7a5186e-ad4e-45c3-a92d-fd41d4b89b03",
//            "PhoneNumber":"01001526337",
//            "PhoneNumberConfirmed":false,
//            "TwoFactorEnabled":false,
//            "LockoutEndDateUtc":null,
//            "LockoutEnabled":false,
//            "AccessFailedCount":0,
//            "Roles":[],
//        "Claims":[],
//        "Logins":[],
//        "Id":"53cb5e30-ee0b-4c55-8f48-01f5cd27189d",
//            "UserName":"amadoknight766@gmail.com"}

    @SerializedName("DisplayName")
    private String DisplayName;
    @SerializedName("VerficationCode")
    private String VerficationCode;
    @SerializedName("Email")
    private String Email;
    @SerializedName("EmailConfirmed")
    private String EmailConfirmed;
    @SerializedName("PasswordHash")
    private String PasswordHash;
    @SerializedName("PhoneNumber")
    private String PhoneNumber;
    @SerializedName("PhoneNumberConfirmed")
    private String PhoneNumberConfirmed;
    @SerializedName("SecurityStamp")
    private String SecurityStamp;
    @SerializedName("TwoFactorEnabled")
    private String TwoFactorEnabled;
    @SerializedName("LockoutEndDateUtc")
    private String LockoutEndDateUtc;
    @SerializedName("LockoutEnabled")
    private String LockoutEnabled;
    @SerializedName("AccessFailedCount")
    private String AccessFailedCount;
    @SerializedName("Roles")
    private String[] Roles;
    @SerializedName("Claims")
    private String[] Claims;
    @SerializedName("Logins")
    private String[] Logins;
    @SerializedName("Id")
    private String Id;
    @SerializedName("UserName")
    private String UserName;

    public RegistrationResponseModel(String displayName, String verficationCode, String email, String emailConfirmed, String passwordHash, String phoneNumber, String phoneNumberConfirmed, String securityStamp, String twoFactorEnabled, String lockoutEndDateUtc, String lockoutEnabled, String accessFailedCount, String[] roles, String[] claims, String[] logins, String id, String userName) {
        DisplayName = displayName;
        VerficationCode = verficationCode;
        Email = email;
        EmailConfirmed = emailConfirmed;
        PasswordHash = passwordHash;
        PhoneNumber = phoneNumber;
        PhoneNumberConfirmed = phoneNumberConfirmed;
        SecurityStamp = securityStamp;
        TwoFactorEnabled = twoFactorEnabled;
        LockoutEndDateUtc = lockoutEndDateUtc;
        LockoutEnabled = lockoutEnabled;
        AccessFailedCount = accessFailedCount;
        Roles = roles;
        Claims = claims;
        Logins = logins;
        Id = id;
        UserName = userName;
    }

    protected RegistrationResponseModel(Parcel in) {
        DisplayName = in.readString();
        VerficationCode = in.readString();
        Email = in.readString();
        EmailConfirmed = in.readString();
        PasswordHash = in.readString();
        PhoneNumber = in.readString();
        PhoneNumberConfirmed = in.readString();
        SecurityStamp = in.readString();
        TwoFactorEnabled = in.readString();
        LockoutEndDateUtc = in.readString();
        LockoutEnabled = in.readString();
        AccessFailedCount = in.readString();
        Roles = in.createStringArray();
        Claims = in.createStringArray();
        Logins = in.createStringArray();
        Id = in.readString();
        UserName = in.readString();
    }

    public static final Creator<RegistrationResponseModel> CREATOR = new Creator<RegistrationResponseModel>() {
        @Override
        public RegistrationResponseModel createFromParcel(Parcel in) {
            return new RegistrationResponseModel(in);
        }

        @Override
        public RegistrationResponseModel[] newArray(int size) {
            return new RegistrationResponseModel[size];
        }
    };

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getVerficationCode() {
        return VerficationCode;
    }

    public void setVerficationCode(String verficationCode) {
        VerficationCode = verficationCode;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmailConfirmed() {
        return EmailConfirmed;
    }

    public void setEmailConfirmed(String emailConfirmed) {
        EmailConfirmed = emailConfirmed;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPhoneNumberConfirmed() {
        return PhoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(String phoneNumberConfirmed) {
        PhoneNumberConfirmed = phoneNumberConfirmed;
    }

    public String getSecurityStamp() {
        return SecurityStamp;
    }

    public void setSecurityStamp(String securityStamp) {
        SecurityStamp = securityStamp;
    }

    public String getTwoFactorEnabled() {
        return TwoFactorEnabled;
    }

    public void setTwoFactorEnabled(String twoFactorEnabled) {
        TwoFactorEnabled = twoFactorEnabled;
    }

    public String getLockoutEndDateUtc() {
        return LockoutEndDateUtc;
    }

    public void setLockoutEndDateUtc(String lockoutEndDateUtc) {
        LockoutEndDateUtc = lockoutEndDateUtc;
    }

    public String getLockoutEnabled() {
        return LockoutEnabled;
    }

    public void setLockoutEnabled(String lockoutEnabled) {
        LockoutEnabled = lockoutEnabled;
    }

    public String getAccessFailedCount() {
        return AccessFailedCount;
    }

    public void setAccessFailedCount(String accessFailedCount) {
        AccessFailedCount = accessFailedCount;
    }

    public String[] getRoles() {
        return Roles;
    }

    public void setRoles(String[] roles) {
        Roles = roles;
    }

    public String[] getClaims() {
        return Claims;
    }

    public void setClaims(String[] claims) {
        Claims = claims;
    }

    public String[] getLogins() {
        return Logins;
    }

    public void setLogins(String[] logins) {
        Logins = logins;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(DisplayName);
        parcel.writeString(VerficationCode);
        parcel.writeString(Email);
        parcel.writeString(EmailConfirmed);
        parcel.writeString(PasswordHash);
        parcel.writeString(PhoneNumber);
        parcel.writeString(PhoneNumberConfirmed);
        parcel.writeString(SecurityStamp);
        parcel.writeString(TwoFactorEnabled);
        parcel.writeString(LockoutEndDateUtc);
        parcel.writeString(LockoutEnabled);
        parcel.writeString(AccessFailedCount);
        parcel.writeStringArray(Roles);
        parcel.writeStringArray(Claims);
        parcel.writeStringArray(Logins);
        parcel.writeString(Id);
        parcel.writeString(UserName);
    }
}
