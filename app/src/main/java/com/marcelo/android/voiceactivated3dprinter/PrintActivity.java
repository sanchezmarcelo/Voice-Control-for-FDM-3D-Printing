package com.marcelo.android.voiceactivated3dprinter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class PrintActivity extends AppCompatActivity {

    WebView thingiverse;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
