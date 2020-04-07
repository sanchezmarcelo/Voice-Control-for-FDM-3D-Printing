package com.marcelo.android.voiceactivated3dprinter;

import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */

public class NetworkRequestGenerator {

    private static final String baseURL = "http://104.145.72.254:11139/";
    //private static final String baseURL = "https//octopi.local";

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

    public static String getOctoPrintIP(){
        return baseURL;
    }

    public void setOctoPrintIP(String str){
        str = baseURL;
    }


}
