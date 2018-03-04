package com.guru.task2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.guru.task2.R;
import com.guru.task2.helper.SetThemeHelper;

public class DetailActivity extends AppCompatActivity {

    TextView name,address,roll,gender;
    SetThemeHelper setThemeHelper =new SetThemeHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeHelper.setCustomTheme(this);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        setThemeHelper.setThemePref(this,this); //Called to alter the preference Bool value inverse and recreate the activity

        return super.onOptionsItemSelected(item);
    }

    void getIntentData()
    {
        Intent recievedIntent = getIntent();
        name.setText("Name:"+recievedIntent.getStringExtra("name"));
        address.setText("Address:"+recievedIntent.getStringExtra("address"));
        roll.setText("RollNo:"+recievedIntent.getStringExtra("rollNo"));
        gender.setText("Gender:"+recievedIntent.getStringExtra("gender"));


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
    }




}
