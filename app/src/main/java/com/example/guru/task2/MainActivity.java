package com.example.guru.task2;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.guru.task2.R.drawable.error_black;

public class MainActivity extends AppCompatActivity {

    String userName, rollNo, userAddress, genderIs;
    EditText name, roll, address;
    // RadioButton male,female,transGender;
    RadioGroup Genders;
    int genderID;
    public ArrayList<UserDetails> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void onSubmit() {
        userName = String.valueOf(name.getText());
        rollNo = String.valueOf(roll.getText());
        userAddress = String.valueOf(address.getText());
        genderID = Genders.getCheckedRadioButtonId();
        Log.d("onclick invoked", "TAG");
        RadioButton gen = findViewById(genderID);
        genderIs = String.valueOf(gen.getText());
        if (validateFields()== true) {
            UserDetails user = new UserDetails(userAddress, userName, genderIs, rollNo);
            Log.d(user.getRollNo(), "TAG");


            if (!userList.contains(user)) {
                userList.add(user);
                Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_SHORT).show();
                clearText();
            } else {
                Toast.makeText(getApplicationContext(), "RollNo already Exist", Toast.LENGTH_SHORT).show();
            }
        }


    }

    void showListActivity() {
        if (!userList.isEmpty()) {
            Intent i = new Intent(getApplicationContext(), ActivityList.class);
            i.putExtra("userArrayList", userList);
            startActivity(i);
        } else
            Toast.makeText(getApplicationContext(), "no users added", Toast.LENGTH_SHORT).show();

        // ArrayList<UserDetails> userDetails = userList ;
        // userDetails.clear();
        // Log.d("MainActivity","size of user list "+userList.size());
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


        }
        else{
            name.requestFocus();
            setToast("Enter Name");

        }
        return false;
    }


    void setToast(String t) {
        Toast.makeText(getBaseContext(), t, Toast.LENGTH_SHORT).show();

    }

}

