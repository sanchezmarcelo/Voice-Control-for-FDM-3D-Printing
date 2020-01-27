package com.marcelo.android.voiceactivated3dprinter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.service.voice.VoiceInteractionService;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;


/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */

public class MainActivity extends AppCompatActivity {

    static final int voiceRequestCode = 1;
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    PageAdapter pageAdapter;
    TextToSpeech textToSpeech;
    EditText userServerIP;
    EditText userAPIKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createThreadPolicy();
        createToolbar();
        createViewPager();
        userServerIP = findViewById(R.id.user_enter_server_ip);
        userAPIKey = findViewById(R.id.user_enter_api_key);
    }

    public void createHotWordDetectionService(){
        VoiceInteractionService hotword = new VoiceInteractionService();
//        hotword.createAlwaysOnHotwordDetector("Hey printer", Locale.ENGLISH,
//                );
    }

    public void createThreadPolicy(){
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void createViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new CommandFragment(), "Command");
        pageAdapter.addFragment(new PrinterStatusFragment(), "Printer Status");

        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void createToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
    }

    public void initTTS(View view){
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                String msg = "Welcome to the voice activated 3D printer application, developed by" +
                        "Marcelo Sanchez. Enjoy.";
                textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null);
            }

        });
    }

    private void sendHomeNetworkRequest(Home home){

        HomeClient client = NetworkRequestGenerator.createService(HomeClient.class);
        Call<Home> call = client.newHomeSession(home);

        Log.d("sendNetworkRequest", "called");

        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                Log.d("onResponse", "success");
                Toast.makeText(MainActivity.this, "Home Executed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Log.d("onResponse", "failure");
                t.printStackTrace();
                    Toast.makeText(MainActivity.this, "F", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendJogNetworkRequest(Jog jog){

        JogClient client = NetworkRequestGenerator.createService(JogClient.class);
        Call<Jog> call = client.newJogSession(jog);

        call.enqueue(new Callback<Jog>() {
            @Override
            public void onResponse(Call<Jog> call, Response<Jog> response) {
                Toast.makeText(MainActivity.this, "Jog Executed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Jog> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendBedNetworkRequest(Bed bed){

        BedClient client = NetworkRequestGenerator.createService(BedClient.class);
        Call<Bed> call = client.newBedSession(bed);

        call.enqueue(new Callback<Bed>() {
            @Override
            public void onResponse(Call<Bed> call, Response<Bed> response) {
                Toast.makeText(MainActivity.this, "Bed Temp Request Executed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Bed> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendNozzleNetworkRequest(Nozzle nozzle){

        NozzleClient client = NetworkRequestGenerator.createService(NozzleClient.class);
        Call<Nozzle> call = client.newNozzleSession(nozzle);

        call.enqueue(new Callback<Nozzle>() {
            @Override
            public void onResponse(Call<Nozzle> call, Response<Nozzle> response) {
                Toast.makeText(MainActivity.this, "Nozzle Temp Request Executed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Nozzle> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Nozzle Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendExtrudeNetworkRequest(Extrude extrude){

        ExtrudeClient client = NetworkRequestGenerator.createService(ExtrudeClient.class);
        Call<Extrude> call = client.newExtrudeSession(extrude);

        call.enqueue(new Callback<Extrude>() {
            @Override
            public void onResponse(Call<Extrude> call, Response<Extrude> response) {
                Toast.makeText(MainActivity.this, "Extrude Request Executed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Extrude> call, Throwable t) {
                Toast.makeText(MainActivity.this, "F", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPrinterStatus(){
    //speaks the nozzle temp and bed temp
    }

    public void getSpeechInput(View view) {
        Intent speechToText = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechToText.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechToText.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 2000);
        startActivityForResult(speechToText, voiceRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("onActivityResult", "Called");

        if(requestCode == voiceRequestCode){

            Log.d("RequestC = VoiceRCode", "Check");

            if(resultCode == RESULT_OK && data != null){

                Log.d("Result Code = RESULT_OK", "Check");

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    //Split result ArrayList
                    ArrayList<String> cmd = new ArrayList<>();

                    for(String str: result){
                        String[]a = str.split(" ");
                        if(a.length == 1) {
                            cmd.add(a[0]);
                        }

                        if(a.length == 2) {
                            cmd.add(a[0]);
                            cmd.add(a[1]);
                        }

                        if(a.length == 3) {
                            cmd.add(a[0]);
                            cmd.add(a[1]);
                            cmd.add(a[2]);
                        }else{

                            Log.d("Word count", "exceeded");
                        }
                    }

                    //JOG REQUEST
                    if(cmd.get(0).equals("jog")){
                        int axisTravelValue = Integer.parseInt(cmd.get(2));
                        if(cmd.contains("x")){
                            Jog jogRequest = new Jog(cmd.get(0), axisTravelValue, 0, 0);
                            sendJogNetworkRequest(jogRequest);

                            final String axis = cmd.get(1);
                            final String amount = cmd.get(2);

                            textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                                @Override
                                public void onInit(int status) {
                                    String jogSuccess = "Jogging" + "x" + amount +"mm";
                                    textToSpeech.speak(jogSuccess, TextToSpeech.QUEUE_FLUSH, null, null);
                                }

                            });

                        }
                        if(cmd.contains("y")){
                            Jog jogRequest = new Jog(cmd.get(0), 0, axisTravelValue, 0);
                            sendJogNetworkRequest(jogRequest);
                        }
                        if(cmd.contains("zed")){
                            Jog jogRequest = new Jog(cmd.get(0), 0, 0, axisTravelValue);
                            sendJogNetworkRequest(jogRequest);
                        }
                    }

                    //HOME REQUEST
                    if(cmd.get(0).equals("home")){

                        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int status) {
                                String homeSuccess = "Now homing all axes.";
                                textToSpeech.speak(homeSuccess, TextToSpeech.QUEUE_FLUSH, null, null);
                            }

                        });



                        Log.d("Home", "requested");
                        String[] axes = {"x", "y", "z"};
                        Home voiceCmd = new Home(cmd.get(0), axes);
                        Log.d("Home", "object created");
                        sendHomeNetworkRequest(voiceCmd);
                        Log.d("sendNetworkRequest", "called");
                    }

                    //NOZZLE REQUEST
                    //Bug setting to zero
                    if(cmd.get(0).equals("nozzle")){
                        int nozzleOnetemp = Integer.parseInt(cmd.get(1));
                        int nozzleTwoTemp = 0;

                        Nozzle.Targets nozzleTargetTemp = new Nozzle.Targets(nozzleOnetemp, nozzleTwoTemp);
                        Nozzle nozzleCmd = new Nozzle("target", nozzleTargetTemp);
                        sendNozzleNetworkRequest(nozzleCmd);
                    }


                    //BED REQUEST
                    if(cmd.get(0).equals("bed")){
                        int bedTemp = Integer.parseInt(cmd.get(1));
                        Bed bedRequest = new Bed("target", bedTemp);
                        sendBedNetworkRequest(bedRequest);
                    }

                    //EXTRUDE REQUEST
                    if(cmd.get(0).equals("extrude")){
                        int extrudeAmount = Integer.parseInt(cmd.get(1));
                        Extrude extrudeRequest = new Extrude(cmd.get(0), extrudeAmount);
                        sendExtrudeNetworkRequest(extrudeRequest);
                    }
            }
        }
    }
}
