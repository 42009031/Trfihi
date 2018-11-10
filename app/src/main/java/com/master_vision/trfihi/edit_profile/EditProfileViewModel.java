package com.master_vision.trfihi.edit_profile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.methods.SharedPreferencesManager;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.profile.model.ProfileModel;
import com.master_vision.trfihi.registration.register._RegistrationService;
import com.master_vision.trfihi.registration.register.model.RegistrationResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class EditProfileViewModel {

    public ObservableField<String> profileImageUrl = new ObservableField<>("http://riptoned.com/wp-content/uploads/2017/01/no-user-image.png");
    public ObservableBoolean maleCheck = new ObservableBoolean(true);
    public ObservableBoolean femaleCheck = new ObservableBoolean(false);
    public ObservableBoolean showImg = new ObservableBoolean(false);
    public ObservableBoolean showGender = new ObservableBoolean(false);
    public ObservableBoolean showDOF = new ObservableBoolean(false);
    public ObservableField<String> dateOfBirthSelected = new ObservableField<>();

    private Context context;
    private _EditProfileService editProfileService;
    public String captureCameraImgUri;
    private String userId;


    public EditProfileViewModel(Context context) {
        this.context = context;
        editProfileService = NetworkClient.getService(_EditProfileService.class);
        userId = SharedPreferencesManager.getStringValue(Helper.USER_ID);
        getUserById(userId);
    }


    public void onGalleryClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        if (ContextCompat.checkSelfPermission(((Activity) context), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(((Activity) context), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, Helper.MY_GALLERY_PERMISSION_CODE);
        } else {
            Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            ((Activity) context).startActivityForResult(intent, Helper.GALLERY_REQUEST);
        }
    }

    public void onCameraClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        if (ContextCompat.checkSelfPermission(((Activity) context), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.CAMERA, Manifest.permission.CAMERA},
                    Helper.MY_CAMERA_PERMISSION_CODE);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            ((Activity) context).startActivityForResult(cameraIntent, Helper.CAMERA_REQUEST);
        }
    }

    public void onSaveClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        updateUserData(userId,
                profileImageUrl.get(),
                dateOfBirthSelected.get(),
                maleCheck.get() ? "male" : "female",
                ""+showImg.get(),
                ""+showDOF.get(),
                ""+showGender.get());
    }

    public void onMaleClick(View view) {
        maleCheck.set(true);
        femaleCheck.set(false);
    }

    public void onFemaleClick(View view) {
        maleCheck.set(false);
        femaleCheck.set(true);
    }

    public void onShowImgClick(View view) {
        showImg.set(showImg.get() ? false : true);
    }

    public void onShowGenderClick(View view) {
        showGender.set(showGender.get() ? false : true);
    }

    public void onShowDOFClick(View view) {
        showDOF.set(showDOF.get() ? false : true);
    }

    public void getDateChanged(DatePicker view, int year, int month, int day) {
        Toast.makeText(view.getContext(), day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
        dateOfBirthSelected.set(day + "/" + (month + 1) + "/" + year);
    }

    public void getUserById(String userId) {
        Helper.displayLoadDialog(context);
        TrfihiApp.getCompositeDisposable().add(editProfileService.getUserById(userId)
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProfileModel>() {
                    @Override
                    public void accept(ProfileModel response) {
                        Helper.dismissLoading();
                        if (response != null) {

                            if (!TextUtils.isEmpty(response.getProfileImageURL())) {
                                profileImageUrl.set(response.getProfileImageURL());
                            }
                            if (response.getGender().equalsIgnoreCase("male")) {
                                maleCheck.set(true);
                                femaleCheck.set(false);
                            } else {
                                maleCheck.set(false);
                                femaleCheck.set(true);
                            }
                            dateOfBirthSelected.set(response.getDOB());

                        } else {
                            Toast.makeText(context, "Server response error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Helper.dismissLoading();
                        throwable.printStackTrace();
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void updateUserData(String ID,
                                String ProfileImageURL,
                                String DOB,
                                String gender,
                                String ShowPicture,
                                String ShowBirthDate,
                                String ShowGender) {

        Helper.displayLoadDialog(context);

        File file = new File(ProfileImageURL);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("ProfileImageURL", file.getName(), requestFile);

        TrfihiApp.getCompositeDisposable().add(editProfileService.updateUser(ID,
                body,
                DOB,
                gender,
                ShowPicture,
                ShowBirthDate,
                ShowGender)
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) {
                        Helper.dismissLoading();
                        if (response != null) {
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Server response error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Helper.dismissLoading();
                        throwable.printStackTrace();
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));

    }

}
