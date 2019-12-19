package com.marcelo.android.voiceactivated3dprinter;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public class Bed {

    String command;
    int target;

    Bed(String cmd, int bedTemp){
        command = cmd;
        target = bedTemp;
    }
}
