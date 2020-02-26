package com.marcelo.android.voiceactivated3dprinter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import static com.marcelo.android.voiceactivated3dprinter.ComputeServerRequestGenerator.retrofit;

public class PrintActivity extends AppCompatActivity {

    WebView thingiverse;
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://ec2-52-14-63-95.us-east-2.compute.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        Intent intent = getIntent();
        String val = intent.getStringExtra("key");
        String thingiverseURL = "https://www.thingiverse.com/search?q=" + val;

        thingiverse = findViewById(R.id.ThingiverseWebView);
        thingiverse.setWebViewClient(new ThingiverseViewClient(thingiverse, thingiverseURL));
        Button printButton = findViewById(R.id.print_button);

    }

    public void printButtonPressed(View view){
        String requestedPrintJobURL = thingiverse.getUrl();
        Toast.makeText(PrintActivity.this, "URL: " + requestedPrintJobURL, Toast.LENGTH_SHORT).show();

        try{
            sliceGCODE(requestedPrintJobURL);
        }catch (Exception e){
            Log.d("CloudSlice", ": " + e);
        }

    }

    private void sliceGCODE(String url){
        STLClient client = retrofit.create(STLClient.class);

        Call<ResponseBody> call = client.newPrintRequest(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(PrintActivity.this, "Cloud Slice Request Executed Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PrintActivity.this, "F", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
