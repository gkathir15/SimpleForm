package com.example.guru.task2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView name,address,roll,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.custom_detail_actionbar);
        actionBar.setDisplayShowCustomEnabled(true);

        name =findViewById(R.id.name);
        address=findViewById(R.id.address);
        roll =findViewById(R.id.roll);
        gender =findViewById(R.id.gender);

        getIntentData();





    }

    void getIntentData()
    {
        Intent recievedIntent = getIntent();
        name.setText("Name:"+recievedIntent.getStringExtra("name"));
        address.setText("Address:"+recievedIntent.getStringExtra("address"));
        roll.setText("RollNo:"+recievedIntent.getStringExtra("rollNo"));
        gender.setText("Gender:"+recievedIntent.getStringExtra("gender"));


    }


}
