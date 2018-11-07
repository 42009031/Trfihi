package com.master_vision.trfihi.common.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.master_vision.trfihi.TrfihiApp;

public class NetworkManager {

    private NetworkCallback callback;
    private Context context = TrfihiApp.context;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateStatus();
        }
    };

    private void updateStatus() {
        if (callback != null) {
            callback.onConnectionChanged(isNetworkAvailable(context));
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void registerContext(NetworkCallback callback) {
        this.callback = callback;
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(receiver, filter);
        updateStatus();
    }

    public void unregisterContext() {
        callback = null;
        context.unregisterReceiver(receiver);
    }

    public interface NetworkCallback {
        void onConnectionChanged(boolean connected);
    }
}