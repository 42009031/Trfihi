package com.master_vision.trfihi.common.network;

import android.text.TextUtils;

import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.methods.SharedPreferencesManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.Nullable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static Retrofit retrofit;
    public static String apiBaseUrl = "http://mate.trfihi2050.com/";
    private static HashMap<String, Object> singletonClasses = new HashMap<>();


    static {
        getRetrofit();
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    String savedToken = SharedPreferencesManager.getStringValue(Helper.TOKEN);
                    if (!TextUtils.isEmpty(savedToken)) {
                        requestBuilder.addHeader("Authorization", savedToken);
                    }
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            }).readTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .addInterceptor(interceptor);


            retrofit = new Retrofit.Builder()
                    .baseUrl(apiBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();

        }

        return retrofit;
    }

    public static <S> S getService(Class<S> serviceClass) {
        for (Map.Entry<String, Object> entry : singletonClasses.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.equals(serviceClass.getName())) {
                return (S) value;
            }
        }
        String key = serviceClass.getName();
        Object value = retrofit.create(serviceClass);
        singletonClasses.put(key, value);
        return (S) value;
    }
}

