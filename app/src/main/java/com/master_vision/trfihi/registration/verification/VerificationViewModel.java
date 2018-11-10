package com.master_vision.trfihi.registration.verification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.methods.SharedPreferencesManager;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.home._main.HomeActivity;
import com.master_vision.trfihi.registration.register._RegistrationService;
import com.master_vision.trfihi.registration.register.model.RegistrationRequestModel;
import com.master_vision.trfihi.registration.register.model.RegistrationResponseModel;
import com.master_vision.trfihi.registration.register.model.TokenResponseModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


public class VerificationViewModel {

    public ObservableField<String> code = new ObservableField<>();

    private RegistrationRequestModel registrationModel;
    private _RegistrationService regService;
    private String phoneVerificationId;
    private Context context;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;

    public VerificationViewModel(Context context, RegistrationRequestModel registrationModel, String phoneVerificationId) {
        this.context = context;
        this.registrationModel = registrationModel;
        this.regService = NetworkClient.getService(_RegistrationService.class);
        this.phoneVerificationId = phoneVerificationId;
    }

    public void onSendVerificationCodeClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        if (TextUtils.isEmpty(code.get())) {
            printToast("Type verification code first");
            return;
        }

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationId, code.get());
        signInWithPhoneAuthCredential(credential);

        Helper.displayLoadDialog((Activity) context);
    }

    public void onResendClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

        setUpVerificationCallbacks();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                registrationModel.getPhoneNumber(),        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                (Activity)context,               // Activity (for callback binding)
                verificationCallbacks);
    }

    public void postUserData() {
        TrfihiApp.getCompositeDisposable().add(regService.postRegistration(registrationModel)
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegistrationResponseModel>() {
                    @Override
                    public void accept(RegistrationResponseModel responseModel) {
                        Helper.dismissLoading();
                        if (responseModel != null) {
                            getToken(responseModel);
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

    public void getToken(final RegistrationResponseModel responseModel) {
        TrfihiApp.getCompositeDisposable().add(regService.getToken("grant_type=password&\n" +
                "username=" + registrationModel.getEmail() + "&\n" +
                "password=" + registrationModel.getPassword())
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TokenResponseModel>() {
                    @Override
                    public void accept(TokenResponseModel tokenResponseModel) {
                        Helper.dismissLoading();
                        if (tokenResponseModel != null) {
                            SharedPreferencesManager.setStringValue(Helper.TOKEN, tokenResponseModel.getToken_type() + " " + tokenResponseModel.getAccess_token());
                            SharedPreferencesManager.setStringValue(Helper.DISPLAY_NAME, registrationModel.getDisplayName());
                            SharedPreferencesManager.setStringValue(Helper.USER_ID, responseModel.getId());
                            SharedPreferencesManager.setStringValue(Helper.USER_NAME, registrationModel.getEmail());
                            SharedPreferencesManager.setStringValue(Helper.PHONE_NUMBER, registrationModel.getPhoneNumber());
                            SharedPreferencesManager.setStringValue(Helper.PASSWORD_HASH, responseModel.getPasswordHash());
                            if(!TextUtils.isEmpty(registrationModel.getProfileImage())){
                                SharedPreferencesManager.setStringValue(Helper.PROFILE_IMAGE, registrationModel.getProfileImage());
                            }
                            (context).startActivity(new Intent(context, HomeActivity.class));
                            ((Activity)context).finish();
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

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    postUserData();
                } else {
                    Helper.dismissLoading();
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(context, "The verification code entered was invalid", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void printToast(String message) {
        Toast.makeText(TrfihiApp.context, message, Toast.LENGTH_LONG).show();
    }

    private void setUpVerificationCallbacks() {
        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Toast.makeText(context, "Complete success :)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Helper.dismissLoading();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Log.d("Register", "Invalid credential: " + e.getLocalizedMessage());
//                    Toast.makeText(context, getString(R.string.innvalidCredential), Toast.LENGTH_LONG).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // SMS quota exceeded
                    Log.d("Register", "SMS Quota exceeded.");
//                    Toast.makeText(context, getString(R.string.smsQota), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                Helper.dismissLoading();
            }
        };
    }


}
