package com.guru.task2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.guru.task2.data_model.UserDetails;
import com.guru.task2.adapter.ListAdapter;
import com.guru.task2.R;
import com.guru.task2.helper.SetThemeHelper;
import com.guru.task2.helper.StudentDBHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ActivityList extends AppCompatActivity {

    ArrayList<UserDetails> studentArray = new ArrayList<UserDetails>();
    static ListAdapter listAdapter;
    StudentDBHelper dbHelper = new StudentDBHelper(this);
    GetDatabasetoList dbAsyncList = new GetDatabasetoList();
    SetThemeHelper setThemeHelper =new SetThemeHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeHelper.setCustomTheme(this);
        setContentView(R.layout.activity_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.custom_list_actionbar);
        actionBar.setDisplayShowCustomEnabled(true);
         //studentArray = dbHelper.retrieve();
        dbAsyncList.execute();
        Log.d("oncreate", String.valueOf(studentArray.size()));

        ListView studentList = findViewById(R.id.studList);


        listAdapter = new ListAdapter(getApplicationContext(), R.id.studList, studentArray);

        studentList.setAdapter(listAdapter);









       // dbAsyncList.execute();
        //studentArray = new GetDatabasetoList().execute()



        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener(

        ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("rollNo", studentArray.get(position).getRollNo());
                i.putExtra("name", studentArray.get(position).getName());
                i.putExtra("gender", studentArray.get(position).getGenderIs());
                i.putExtra("address", studentArray.get(position).getAddress());
                startActivity(i);


            }
        });
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



    private  class GetDatabasetoList extends AsyncTask<Void, Void, ArrayList<UserDetails>>
    {
        ProgressBar progressBar;

        @Override
        protected ArrayList<UserDetails> doInBackground(Void... values) {
            Log.d("Async", "DoInBG");
            ArrayList<UserDetails> templist = dbHelper.retrieve();

            Log.d("arraylist size", String.valueOf(templist.size()));
            return templist;

        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(ArrayList<UserDetails> tempList)
        {
            super.onPostExecute(tempList);
            ArrayList<UserDetails> tempList1 =tempList;

            Log.d("onpost","async");
            Log.d("arraylist size tmp", String.valueOf(tempList.size()));
            Log.d("arraylist size onPost", String.valueOf(studentArray.size()));
            studentArray.addAll(tempList);
            listAdapter.notifyDataSetChanged();






            //progressBar.setVisibility(View.GONE);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
       new GetDatabasetoList().cancel(true);
    }
}
