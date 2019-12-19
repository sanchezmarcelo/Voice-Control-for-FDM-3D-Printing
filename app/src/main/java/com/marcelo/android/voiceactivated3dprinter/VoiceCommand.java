package com.marcelo.android.voiceactivated3dprinter;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public class VoiceCommand {

    String command;
    String[] axes;

    VoiceCommand(String cmd, String[] a){
        command = cmd;
        axes = a;
    }



}
