package com.marcelo.android.voiceactivated3dprinter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.marcelo.android.voiceactivated3dprinter.ComputeServerRequestGenerator.httpClient;
import static com.marcelo.android.voiceactivated3dprinter.ComputeServerRequestGenerator.retrofit;

public class PrintActivity extends AppCompatActivity {

    WebView thingiverse;

    OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    private Retrofit builder = new Retrofit.Builder()
            .baseUrl("http://ec2-52-14-63-95.us-east-2.compute.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClientBuilder.build()).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        Intent intent = getIntent();
        String val = intent.getStringExtra("key");
        String thingiverseURL = "https://www.thingiverse.com/search?q=" + val +
                "&type=things&sort=relevant";
        thingiverse = findViewById(R.id.ThingiverseWebView);
        WebSettings settings = thingiverse.getSettings();
        settings.setDomStorageEnabled(true);
        thingiverse.setWebViewClient(new ThingiverseViewClient(thingiverse, thingiverseURL));
        Button printButton = findViewById(R.id.print_button);
    }

    public void printButtonPressed(View view){
        String requestedPrintJobURL = thingiverse.getUrl();
        String test = thingiverse.getOriginalUrl();
        //Toast.makeText(PrintActivity.this, "URL: " + test, Toast.LENGTH_SHORT).show();
        try{
            sliceGCODE(requestedPrintJobURL);
        }catch (Exception e){
            Log.d("CloudSlice", ": " + e);
        }
        //redirectToGcodePage();
    }

    public void redirectToHome(){
        Intent backToAppHomePage = new Intent(PrintActivity.this, MainActivity.class);
        PrintActivity.this.startActivity(backToAppHomePage);
    }

    public void redirectToGcodePage(){
        // redirect to download link for gcode
        thingiverse.setWebViewClient(new ThingiverseViewClient(thingiverse, "http://ec2-52-14-63-95.us-east-2.compute.amazonaws.com/gcode.html"));
        thingiverse.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                Toast.makeText(PrintActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public AlertDialog createProgressBar(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(PrintActivity.this);
        LayoutInflater inflater = PrintActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_bar, null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
        return dialog;
    }


    private void sliceGCODE(String url){
        STLClient client = retrofit.create(STLClient.class);
        Call<ResponseBody> call = client.newPrintRequest(url);
        final AlertDialog dialog = createProgressBar();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //Toast.makeText(PrintActivity.this, "Cloud Slice Request Executed Successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                redirectToHome();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PrintActivity.this, "Cloud Slice: No response ", Toast.LENGTH_SHORT).show();
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
