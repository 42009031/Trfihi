package com.master_vision.trfihi.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.master_vision.trfihi.R;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.model.GenericModel;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.databinding.DialogLoginPhoneNoBinding;
import com.master_vision.trfihi.home._main.HomeActivity;
import com.master_vision.trfihi.registration.register._RegistrationService;
import com.master_vision.trfihi.registration.register.adapter.FlagsSpinnerAdapter;
import com.master_vision.trfihi.registration.register.model.RegistrationRequestModel;
import com.master_vision.trfihi.registration.register.model.TokenResponseModel;
import com.master_vision.trfihi.registration.verification.VerificationActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


public class LoginViewModel extends BaseObservable {

    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<Boolean> hidePassword = new ObservableField<>(true);
    public ObservableField<Integer> hidePassImg = new ObservableField<>(R.drawable.ic_visibility);
    private _RegistrationService regService;

    private Context context;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;


    // dialog
    public ObservableField<String> mobileDialog = new ObservableField<>();
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

    // constructor
    public LoginViewModel(Context context) {
        this.context = context;
        this.regService = NetworkClient.getService(_RegistrationService.class);
        this.flagsSpinnerAdapter = new FlagsSpinnerAdapter(context, countryCodeList);
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

    public void onSelectItem(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        Log.e("position ==> ", "" + position);
        countryCodeSelectedItem.set(countryCodeList.get(position).getId());
        Toast.makeText(context, countryCodeSelectedItem.get(), Toast.LENGTH_SHORT).show();
    }

    //Helper methods
    private void printToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

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
                ((Activity) context).
                        startActivity(new Intent((Activity) context, VerificationActivity.class)
                                .putExtra("RegistrationModel", requestModel)
                                .putExtra("PhoneVerificationId", verificationId)
                        );
            }
        };
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


    // APIs
    private void getToken(String email, String password) {
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


    //dialog
    private void displayDialogToEnterPhoneNo(final RegistrationRequestModel regReqParam) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        final DialogLoginPhoneNoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_login_phone_no, null, false);
        dialog.setContentView(binding.getRoot());
        binding.setLoginVM(this);

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
