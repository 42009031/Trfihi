package com.master_vision.trfihi.registration.register.view_model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
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
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.model.GenericModel;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.adapter.SpinnerAdapter;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.registration.register.RegistrationWS;
import com.master_vision.trfihi.registration.register._RegistrationService;
import com.master_vision.trfihi.registration.register.adapter.FlagsSpinnerAdapter;
import com.master_vision.trfihi.registration.register.model.RegistrationRequestModel;
import com.master_vision.trfihi.registration.register.model.RegistrationResponseModel;
import com.master_vision.trfihi.registration.terms_conditions.TermsActivity;
import com.master_vision.trfihi.registration.verification.VerificationActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class RegistrationViewModel extends BaseObservable {

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> mobile = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> confirmPassword = new ObservableField<>();

    public ObservableField<Boolean> hideConfirmPassword = new ObservableField<>(true);
    public ObservableField<Integer> hideConfirmPassImg = new ObservableField<>(R.drawable.ic_visibility);

    public ObservableField<Boolean> hidePassword = new ObservableField<>(true);
    public ObservableField<Integer> hidePassImg = new ObservableField<>(R.drawable.ic_visibility);

    public FlagsSpinnerAdapter flagsSpinnerAdapter;
    private ObservableArrayList<GenericModel> countryCodeList = new ObservableArrayList<GenericModel>() {{
        add(new GenericModel("+2", R.drawable.flag_egypt));
        add(new GenericModel("+966", R.drawable.flag_saudia));
        add(new GenericModel("+971", R.drawable.flag_emirates));
        add(new GenericModel("+965", R.drawable.flag_kuwait));
        add(new GenericModel("+968", R.drawable.flag_oman));
        add(new GenericModel("+212", R.drawable.flag_moracco));
    }};
    public ObservableField<String> countryCodeSelectedItem = new ObservableField<>();
    public ObservableField<Integer> spinnerSelectionPosition = new ObservableField<>();


    private Context context;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;

    public RegistrationViewModel(Activity context) {
        this.context = context;
        this.flagsSpinnerAdapter = new FlagsSpinnerAdapter(context,  countryCodeList);
    }

    // button click listeners
    public void onRegisterClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);

        String name_str = name.get();
        String email_str = email.get();
        String password_str = password.get();
        String confirmPass_str = confirmPassword.get();
        String mobile_str = mobile.get();

        if (hasEmptyVal(name_str, "Please type your name.")) {
            return;
        }
        if (hasEmptyVal(email_str, "Please type your E-Mail address.")) {
            return;
        }
        if (hasEmptyVal(password_str, "Please type your password.")) {
            return;
        }
        if (hasEmptyVal(confirmPass_str, "Please confirm your password.")) {
            return;
        }
        if (hasEmptyVal(mobile_str, "Please type your mobile no.")) {
            return;
        }

        if (!Helper.isValidEmail(email_str)) {
            printToast("Invalid E-Mail address");
            return;
        }
        assert password_str != null;
        if (!Helper.isValidPassword(password_str)) {
            printToast("your password must contain [number, capital, small, number] characters and > 6");
            return;
        }
        if (!password_str.equals(confirmPass_str)) {
            printToast("Error in confirm password");
            return;
        }

        String phoneNumber = countryCodeSelectedItem.get() + mobile_str;
        setUpVerificationCallbacks(new RegistrationRequestModel(email_str,
                name_str,
                email_str,
                phoneNumber,
                password_str));

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                (Activity)context,               // Activity (for callback binding)
                verificationCallbacks);

        Helper.displayLoadDialog((Activity)context);
    }

    public void onHideConfirmPassClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        if (hideConfirmPassword.get()) {
            hideConfirmPassword.set(false);
            hideConfirmPassImg.set(R.drawable.ic_visibility_off);
            notifyChange();
        } else {
            hideConfirmPassword.set(true);
            hideConfirmPassImg.set(R.drawable.ic_visibility);
            notifyChange();
        }
    }

    public void onHidePassClick(View view) {
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

    public void onTermsClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        ((Activity) view.getContext()).startActivity(new Intent((Activity) view.getContext(), TermsActivity.class));
    }

    public void onSelectItem(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        Log.e("position ==> ", "" + position);
        countryCodeSelectedItem.set(countryCodeList.get(position).getId());
        Toast.makeText(context, countryCodeSelectedItem.get(), Toast.LENGTH_SHORT).show();
    }


    // Helper methods
    private void printToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    private boolean hasEmptyVal(String val, String errorMsg) {
        if (TextUtils.isEmpty(val)) {
            printToast(errorMsg);
            return true;
        }
        return false;
    }

    // APIs

    private void setUpVerificationCallbacks(final RegistrationRequestModel requestModel) {
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
                resendToken = token;
                ((Activity) context).
                        startActivity(new Intent((Activity) context, VerificationActivity.class)
                                .putExtra("RegistrationModel", requestModel)
                                .putExtra("PhoneVerificationId", verificationId)
                        );
            }
        };
    }

}
