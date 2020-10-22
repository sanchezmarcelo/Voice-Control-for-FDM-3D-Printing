package com.marcelo.android.voiceactivated3dprinter;

import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComputeServerRequestGenerator {
    private static final String baseURL = "http://ec2-xxxxxxxxxxxx-2.compute.amazonaws.com/";

    static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create());

    static Retrofit retrofit = builder
            .client(httpClient.build()).build();

    public static <S> S createService(Class<S> serviceClass){
        Log.d("Service Created", "success");
        return retrofit.create(serviceClass);
    }
}
