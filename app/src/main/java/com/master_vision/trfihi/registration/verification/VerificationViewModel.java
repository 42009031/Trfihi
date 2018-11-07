package com.master_vision.trfihi.registration.verification;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.registration.register._RegistrationService;
import com.master_vision.trfihi.registration.register.model.RegistrationRequestModel;
import com.master_vision.trfihi.registration.register.model.RegistrationResponseModel;

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

        Helper.displayLoadDialog((Activity)context);
    }

    public void onResendClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }

    public void postUserData(RegistrationRequestModel requestModel) {

        TrfihiApp.getCompositeDisposable().add(regService.postRegistration(requestModel)
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegistrationResponseModel>() {
                    @Override
                    public void accept(RegistrationResponseModel responseModel) {
                        Helper.dismissLoading();
                        if (responseModel != null) {

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
                    postUserData(registrationModel);
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

//    private void resendVerificationCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token) {
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phoneNumber,        // Phone number to verify
//                60,                 // Timeout duration
//                TimeUnit.SECONDS,   // Unit of timeout
//                this,               // Activity (for callback binding)
//                mCallbacks,         // OnVerificationStateChangedCallbacks
//                token);             // ForceResendingToken from callbacks
//    }
}
