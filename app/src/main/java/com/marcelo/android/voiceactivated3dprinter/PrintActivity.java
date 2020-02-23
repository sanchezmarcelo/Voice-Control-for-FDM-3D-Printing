package com.marcelo.android.voiceactivated3dprinter;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class Print extends AppCompatActivity {
    String userRequestedSTL = "car";
    String thingiverseURL = "https://www.thingiverse.com/search?q=" + userRequestedSTL;
    WebView thingiverse;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        createToolbar();
        
        thingiverse = findViewById(R.id.ThingiverseWebView);
        thingiverse.setWebViewClient(new ThingiverseViewClient(thingiverse, thingiverseURL));
        Button printButton = findViewById(R.id.print_button);

    }

    public void printButtonPressed(View view){
        String requestedPrintJobURL = thingiverse.getUrl();
        Toast.makeText(Print.this, "URL: " + requestedPrintJobURL, Toast.LENGTH_SHORT).show();
    }

    public void createToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Allows for the back button in this activity to return to the previous activity.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
