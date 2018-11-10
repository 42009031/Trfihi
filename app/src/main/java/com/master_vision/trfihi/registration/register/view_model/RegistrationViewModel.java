package com.master_vision.trfihi.registration.register.view_model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.model.GenericModel;
import com.master_vision.trfihi.common.methods.Helper;

import com.master_vision.trfihi.registration.register.adapter.FlagsSpinnerAdapter;
import com.master_vision.trfihi.registration.register.model.RegistrationRequestModel;
import com.master_vision.trfihi.databinding.DialogPhoneNoBinding;
import com.master_vision.trfihi.registration.terms_conditions.TermsActivity;
import com.master_vision.trfihi.registration.verification.VerificationActivity;

import java.util.concurrent.TimeUnit;


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

    // dialog
    public ObservableField<String> mobileDialog = new ObservableField<>();


    private Context context;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;

    // constructor
    public RegistrationViewModel(Activity context) {
        this.context = context;
        this.flagsSpinnerAdapter = new FlagsSpinnerAdapter(context, countryCodeList);
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
        sendVerificationCode(new RegistrationRequestModel(name_str,
                email_str,
                phoneNumber,
                password_str,
                ""));
    }

    public void sendVerificationCode(RegistrationRequestModel regReqParam) {
        String mobileNo = regReqParam.getPhoneNumber();
        if (TextUtils.isEmpty(mobileNo)) {
            // display Dialog To enter phone number
            displayDialogToEnterPhoneNo(regReqParam);
            return;
        }
        setUpVerificationCallbacks(regReqParam);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobileNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                (Activity) context,               // Activity (for callback binding)
                verificationCallbacks);

        Helper.displayLoadDialog((Activity) context);
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
        (view.getContext()).startActivity(new Intent((Activity) view.getContext(), TermsActivity.class));

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


    //dialog
    private void displayDialogToEnterPhoneNo(final RegistrationRequestModel regReqParam) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        final DialogPhoneNoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_phone_no, null, false);
        dialog.setContentView(binding.getRoot());
        binding.setRegVM(this);

        dialog.show();

        binding.cancelAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        binding.closeDialogAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        binding.saveAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mobileDialog.get())){
                    printToast(context.getResources().getString(R.string.mobile_validation));
                    return;
                }
                String phoneNumber = countryCodeSelectedItem.get() + mobileDialog.get();
                RegistrationRequestModel editRegReqParam = regReqParam;
                editRegReqParam.setPhoneNumber(phoneNumber);
                setUpVerificationCallbacks(editRegReqParam);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        (Activity) context,               // Activity (for callback binding)
                        verificationCallbacks);

                Helper.displayLoadDialog((Activity) context);
                dialog.dismiss();
            }
        });

    }
}
