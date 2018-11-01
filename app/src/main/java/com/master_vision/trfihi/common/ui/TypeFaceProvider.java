package com.master_vision.trfihi.common.ui;

import android.graphics.Typeface;

import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.methods.SharedPreferencesManager;


public class TypeFaceProvider {

    public static Typeface lightFace;

    private TypeFaceProvider() {
    }

    public static Typeface getTypeFace() {

            if (SharedPreferencesManager.getStringValue(Helper.LOCALE).equals(Helper.AR)) {
                lightFace = Typeface.createFromAsset(TrfihiApp.context.getAssets(), "fonts/Lato-Regular.ttf");
            } else {
                lightFace = Typeface.createFromAsset(TrfihiApp.context.getAssets(), "fonts/Lato-Regular.ttf");
            }

        return lightFace;
    }

    public static Typeface getArTypeface(){
        return Typeface.createFromAsset(TrfihiApp.context.getAssets(), "fonts/Lato-Regular.ttf");
    }

    public static Typeface getEnTypeface(){
        return Typeface.createFromAsset(TrfihiApp.context.getAssets(), "fonts/Lato-Regular.ttf");
    }

}
