package com.example.guru.task2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {

    ArrayList<UserDetails> studentArray = new ArrayList<UserDetails>() ;
    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent i =getIntent();
        studentArray = (ArrayList<UserDetails>) i.getSerializableExtra("userArrayList");
        ListView studentList = findViewById(R.id.studList);
         listAdapter = new ListAdapter(getApplicationContext(),R.id.studList,studentArray);
        studentList.setAdapter(listAdapter);

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener(

        ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivityToData(position);



            }
        });
    }


    private  void startActivityToData(int position)
    {
        Intent i = new Intent(getApplicationContext(),DetailActivity.class);
        i.putExtra("rollNo",studentArray.get(position).getRollNo());
        i.putExtra("name",studentArray.get(position).getName());
        i.putExtra("gender",studentArray.get(position).getGenderIs());
        i.putExtra("address",studentArray.get(position).getAddress());
        startActivity(i);

    }
}
