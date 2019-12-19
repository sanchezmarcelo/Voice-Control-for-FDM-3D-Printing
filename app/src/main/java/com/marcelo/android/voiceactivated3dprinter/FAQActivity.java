package com.marcelo.android.voiceactivated3dprinter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FAQActivity extends AppCompatActivity {

    private ArrayList<String> questionList = new ArrayList<>();
    private ArrayList<String> answerList = new ArrayList<>();
    RecyclerView recyclerView;
    FAQRecyclerViewAdapter recyclerViewAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_faq_list);
        createRecyclerView();
        createToolbar();
        createFAQMessageList();
    }

    public void createToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    //Allows for the back button in this activity to return to the previous activity.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void createRecyclerView(){
        recyclerView = findViewById(R.id.list);
        recyclerViewAdapter = new FAQRecyclerViewAdapter(questionList, answerList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void createFAQMessageList(){
        FAQ faqOne = new FAQ("What kinds of commands can I give the 3D Printer?", "This application" +
                " contains two primary features. Feature A: allows you to instruct the printer " +
                "to perform a variety of mechanical movements. " +
                "Feature B: allows you to instruct the printer to perform a print job");
        questionList.add(faqOne.getQuestion());
        answerList.add(faqOne.getAnswer());

        FAQ faqTwo = new FAQ("What is an example of Feature A?", "You can issue a print head, tool, or bed command. " +
                "For example, you can say something " +
                "like: \n \n\"Jog X 20\" to Jog the X axis 20 mm in the positive direction." +
                "\n or, \"Home printer\" to home the print head in all axes." +
                "\n or, \"Nozzle 200\" to set the nozzle temperature to 200C." +
                "\n or, \"Extrude 10\" to extrude 10 mm worth of filament." +
                "\n or, \"Retract 5\" to retract 5 mm worth of filament." +
                "\n or, \"Flow rate 115%\" to increase the flowrate." +
                "\n or, \"Bed 60\" to set the bed temperature to 60.");
        questionList.add(faqTwo.getQuestion());
        answerList.add(faqTwo.getAnswer());

        FAQ faqThree = new FAQ("What is an example of Feature B?", "Oof, that ones " +
                "gonna take a hot minute dude.");
        questionList.add(faqThree.getQuestion());
        answerList.add(faqThree.getAnswer());

        FAQ faqFour = new FAQ("What is OctoPrint?", "OctoPrint refers to a set of software " +
                "that is used to monitor your 3D printer remotely. It also has a snappy web interface. This software is required" +
                " to be pre-configured in order to run this application successfully.");
        questionList.add(faqFour.getQuestion());
        answerList.add(faqFour.getAnswer());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
