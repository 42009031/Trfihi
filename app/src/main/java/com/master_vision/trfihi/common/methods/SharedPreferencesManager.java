package com.master_vision.trfihi.common.methods;

import android.content.Context;
import android.content.SharedPreferences;
import com.master_vision.trfihi.TrfihiApp;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS = "APP_SETTINGS";

    private SharedPreferencesManager() {}

    private static SharedPreferences getSharedPreferences() {
        return TrfihiApp.context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static String getStringValue(String KEY) {
        return getSharedPreferences().getString(KEY , "");
    }

    public static void setStringValue(String KEY, String newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(KEY , newValue);
        editor.apply();
    }


    public static void clearSharedPreference(){
        getSharedPreferences().edit().clear().apply();
    }
}