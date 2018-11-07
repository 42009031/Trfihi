package com.master_vision.trfihi.registration.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.registration.register.model.RegistrationRequestModel;
import com.master_vision.trfihi.registration.register.model.RegistrationResponseModel;
import com.master_vision.trfihi.registration.verification.VerificationActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


public class RegistrationWS {

    private _RegistrationService regService;
    private Context context;

    public RegistrationWS(Context context) {
        regService = NetworkClient.getService(_RegistrationService.class);
        this.context = context;
    }





}
