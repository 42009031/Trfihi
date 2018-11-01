package com.master_vision.trfihi.registration.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.model.GenericModel;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.adapter.SpinnerAdapter;
import com.master_vision.trfihi.registration.terms_conditions.TermsActivity;

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

    public SpinnerAdapter<GenericModel> spinnerAdapter;
    public ObservableArrayList<GenericModel> countryCodeList = new ObservableArrayList<>();
    public ObservableField<String> countryCodeSelectedItem = new ObservableField<>();
    public ObservableField<Integer> spinnerSelectionPosition = new ObservableField<>();


    public Context context ;
    public RegistrationViewModel(Activity context) {
        this.context = context;
        spinnerAdapter = new SpinnerAdapter<>(context, R.layout.adapter_drop_down_spinner, countryCodeList);
    }


    // button click listeners
    public void onRegisterClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
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

    public void onFacebookClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }

    public void onGoogleClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        spinnerSelectionPosition.set(2);
    }

    public void onTwitterClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        countryCodeList.add(new GenericModel("+2", "+2 (EGY)"));
        countryCodeList.add(new GenericModel("+999", "+999 (Kuwait)"));
        countryCodeList.add(new GenericModel("+996", "+996 (KSU)"));
        spinnerAdapter.setListItems(countryCodeList);
    }

    public void onTermsClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        ((Activity) view.getContext()).startActivity(new Intent((Activity) view.getContext(), TermsActivity.class));
    }

    public void onSelectItem(AdapterView<?> parentView, View selectedItemView, int position, long id){
        Log.e("position ==> ", ""+position );
        countryCodeSelectedItem.set(countryCodeList.get(position).getId());
        Toast.makeText(context, countryCodeSelectedItem.get(), Toast.LENGTH_SHORT).show();
    }



}
