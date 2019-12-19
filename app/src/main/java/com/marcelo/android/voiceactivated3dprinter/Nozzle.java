package com.marcelo.android.voiceactivated3dprinter;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public class Nozzle {

    String command;
    Targets targets;

    public static class Targets{
        int tool0, tool1;
        Targets(int a, int b){
            tool0 = a;
            tool1 = b;
        }
    }


    Nozzle(String cmd, Targets t){
        command = cmd;
        targets = t;
    }
}
