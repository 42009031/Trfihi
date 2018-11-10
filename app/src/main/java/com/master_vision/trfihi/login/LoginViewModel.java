package com.master_vision.trfihi.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.methods.SharedPreferencesManager;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.home._main.HomeActivity;
import com.master_vision.trfihi.registration.register._RegistrationService;
import com.master_vision.trfihi.registration.register.model.RegistrationResponseModel;
import com.master_vision.trfihi.registration.register.model.TokenResponseModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


public class LoginViewModel extends BaseObservable {

    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<Boolean> hidePassword = new ObservableField<>(true);
    public ObservableField<Integer> hidePassImg = new ObservableField<>(R.drawable.ic_visibility);
    private _RegistrationService regService;
    private Context context;

    public LoginViewModel(Context context) {
        this.regService = NetworkClient.getService(_RegistrationService.class);
        this.context = context;
    }


    // buttons on click listener

    public void onShowPasswordClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        if (hidePassword.get()) {
            hidePassword.set(false);
            hidePassImg.set(R.drawable.ic_visibility_off);
            notifyChange();
        } else {
            hidePassword.set(true);
            hidePassImg.set(R.drawable.ic_visibility);
            notifyChange();
        }
    }

    public void onLoginClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

        if (TextUtils.isEmpty(email.get())) {
            printToast("");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            printToast("");
            return;
        }
        getToken(email.get(), password.get());

    }

    public void onFacebookClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }

    public void onGoogleClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }

    public void onTwitterClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void printToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void getToken(String email, String password) {
        TrfihiApp.getCompositeDisposable().add(regService.getToken("grant_type=password&\n" +
                "username=" + email + "&\n" +
                "password=" + password)

                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TokenResponseModel>() {
                    @Override
                    public void accept(TokenResponseModel tokenResponseModel) {
                        Helper.dismissLoading();
                        if (tokenResponseModel != null) {
//                            SharedPreferencesManager.setStringValue(Helper.TOKEN, tokenResponseModel.getToken_type() + " " + tokenResponseModel.getAccess_token());
//                            SharedPreferencesManager.setStringValue(Helper.DISPLAY_NAME, tokenResponseModel.getDisplayName());
//                            SharedPreferencesManager.setStringValue(Helper.USER_NAME, tokenResponseModel.getEmail());
//                            SharedPreferencesManager.setStringValue(Helper.PHONE_NUMBER, tokenResponseModel.getPhoneNumber());
//                            SharedPreferencesManager.setStringValue(Helper.PASSWORD_HASH, tokenResponseModel.getPasswordHash());

                            context.startActivity(new Intent(context, HomeActivity.class));
                            ((Activity) context).finish();
                        } else {
                            printToast("Server response error !!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Helper.dismissLoading();
                        throwable.printStackTrace();
                        printToast(throwable.getMessage());
                    }
                }));
    }


}
