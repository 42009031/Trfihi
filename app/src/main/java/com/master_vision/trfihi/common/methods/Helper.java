package com.master_vision.trfihi.common.methods;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.animation.AlphaAnimation;


public class Helper {

    public final static String LOCALE = "locale";
    public final static String AR = "ar";
    public final static String EN = "en";
    public final static AlphaAnimation BtnClickAnimation = new AlphaAnimation(1F, 0.6F);
    public final static String TOKEN = "token";
    private final static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final static String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_^&+=-])(?=\\S+$).{6,}$";
    private static ProgressDialog progress;

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidPassword(String target) {
//        && target.length() > 6
        return (target.matches(passwordPattern) && target.length() > 0);
    }


    public static void displayLoadDialog(Context context) {

        progress = new ProgressDialog(context);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    public static void dismissLoading() {
        if (progress != null) {
            progress.dismiss();
        }
    }

}
