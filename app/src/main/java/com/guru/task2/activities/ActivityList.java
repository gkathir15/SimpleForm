package com.guru.task2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.guru.task2.data_model.UserDetails;
import com.guru.task2.adapter.ListAdapter;
import com.guru.task2.R;
import com.guru.task2.helper.StudentDBHelper;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {

    ArrayList<UserDetails> studentArray = new ArrayList<UserDetails>() ;
    private ListAdapter listAdapter;
    StudentDBHelper dbHelper = new StudentDBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.custom_list_actionbar);
        actionBar.setDisplayShowCustomEnabled(true);
        studentArray = dbHelper.retrieve();
        ListView studentList = findViewById(R.id.studList);
         listAdapter = new ListAdapter(getApplicationContext(),R.id.studList,studentArray);
        studentList.setAdapter(listAdapter);

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener(

        ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(),DetailActivity.class);
                i.putExtra("rollNo",studentArray.get(position).getRollNo());
                i.putExtra("name",studentArray.get(position).getName());
                i.putExtra("gender",studentArray.get(position).getGenderIs());
                i.putExtra("address",studentArray.get(position).getAddress());
                startActivity(i);



            }
        });
    }


 /*   private class GetDatabasetoList extends AsyncTask<ArrayList<UserDetails>,String,String>
    {



        @Override
        protected String doInBackground(ArrayList<UserDetails>) {
            super.doInBackground(ArrayList<UserDetails>)
            arrayLists = dbHelper.retrieve();

            return null;
        }
    }*/



}
