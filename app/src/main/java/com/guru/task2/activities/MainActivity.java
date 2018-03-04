package com.guru.task2.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.guru.task2.R;
import com.guru.task2.helper.SetThemeHelper;
import com.guru.task2.helper.AppDBHelper;
import com.guru.task2.data_model.UserDetails;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    String userName, rollNo, userAddress, genderIs;
    EditText name, roll, address;
    // RadioButton male,female,transGender;
    RadioGroup Genders;
    int genderID;
    public ArrayList<UserDetails> userList = new ArrayList<>();
    AppDBHelper studentDB;
    SetThemeHelper setThemeHelper =new SetThemeHelper();
    //Activity activity =new Activity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setThemeHelper.writePreferenceData(this);
        setThemeHelper.setCustomTheme(this);
        //Setting up app theme manually
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.custom_form_actionbar);
        actionBar.setDisplayShowCustomEnabled(true);





        name = findViewById(R.id.name);
        roll = findViewById(R.id.roll);
        address = findViewById(R.id.address);
        Genders = findViewById(R.id.genderRadio);
        Button submit = findViewById(R.id.submit);
        final Button showList = findViewById(R.id.viewList);

        //Creation and saving data to Shared preferences,
       // SharedPreferences sharedPreferences = getSharedPreferences(R.string.app_name+"ThemeData",MODE_PRIVATE);



       // Log.d("Theme",);



        studentDB = new AppDBHelper(this);


        submit.setOnClickListener((new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                onSubmit();

            }
        }));

        showList.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListActivity();
            }
        }));




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







    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void onSubmit() {
        userName = String.valueOf(name.getText());
        rollNo = String.valueOf(roll.getText());
        userAddress = String.valueOf(address.getText());
        genderID = Genders.getCheckedRadioButtonId();
        Log.d("onclick invoked", "TAG");
        RadioButton gen = findViewById(genderID);
        genderIs = String.valueOf(gen.getText());
        if (validateFields() == true) {
            UserDetails user = new UserDetails(userAddress, userName, genderIs, rollNo);
            Log.d(user.getRollNo(), "TAG");
            if (studentDB.updateDB(user) == -1) {

                setToast("Roll no already present");
                roll.requestFocus();
            } else {

                setToast("Data Inserted");
                clearText();
            }


        }


    }

    void showListActivity() {
        // userList = studentDB.retrieveStudentData();
        Intent i = new Intent(getApplicationContext(), ActivityList.class);
        startActivity(i);


    }



    void clearText() {
        name.getText().clear();
        roll.getText().clear();
        address.getText().clear();
        name.requestFocus();
    }

    boolean validateData(TextView v) {
        if (v.getText().toString().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    Boolean validateFields() {
        if (validateData(name) == true) {
            if (validateData(roll) == true) {
                if (validateData(address) == true) {

                    return true;
                } else {
                    address.requestFocus();
                    setToast("Enter Address");

                }
            } else {
                roll.requestFocus();
                setToast("Enter Roll");

            }


        } else {
            name.requestFocus();
            setToast("Enter Name");

        }
        return false;
    }


    void setToast(String t) {
        Toast.makeText(getBaseContext(), t, Toast.LENGTH_SHORT).show();

    }



}

