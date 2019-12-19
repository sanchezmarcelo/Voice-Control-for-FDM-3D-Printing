package com.marcelo.android.voiceactivated3dprinter;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public class Jog {

    String command;
    int x, y, z;

    Jog(String cmd, int a, int b, int c) {
        command = cmd;
        x = a;
        y = b;
        z = c;
    }
}
