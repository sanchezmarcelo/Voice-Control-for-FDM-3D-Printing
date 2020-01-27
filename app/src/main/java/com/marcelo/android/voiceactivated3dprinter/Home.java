package com.marcelo.android.voiceactivated3dprinter;

import retrofit2.Call;

/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public class Home implements HomeClient{

    String command;
    String[] axes;

    Home(String cmd, String[] a){
        command = cmd;
        axes = a;
    }


    @Override
    public String getKey() {
        return HomeClient.key;
    }

    @Override
    public Call<Home> newHomeSession(Home home) {
        return null;
    }
}
