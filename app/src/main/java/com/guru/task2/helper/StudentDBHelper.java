package com.guru.task2.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.guru.task2.data_model.UserDetails;

import java.util.ArrayList;

import static com.guru.task2.constants.StudentListContract.*;

/**
 * Created by User on 23-02-2018.
 */

public class StudentDBHelper extends SQLiteOpenHelper {


    public StudentDBHelper(Context context) {
        super(context, StudentEntry.DATABASE_NAME, null, DATABASE_VERSION);
    }




    public static int DATABASE_VERSION = 1;


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_STUDENT_DB = "CREATE TABLE" + StudentEntry.TABLE_NAME
                + '(' + StudentEntry.COLOUMN_NAME_ROLL + " INTEGER PRIMARY KEY,"
                + StudentEntry.COLOUMN_NAME_NAME + " TEXT,"
                + StudentEntry.COLOUMN_NAME_ADDRESS + " TEXT,"
                + StudentEntry.COLOUMN_NAME_GENDER + " TEXT);";

        db.execSQL(SQL_CREATE_STUDENT_DB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void updateDB(UserDetails userDetails) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(StudentEntry.COLOUMN_NAME_ROLL, Integer.parseInt(userDetails.getRollNo()));
        content.put(StudentEntry.COLOUMN_NAME_NAME, userDetails.getName());
        content.put(StudentEntry.COLOUMN_NAME_ADDRESS, userDetails.getAddress());
        content.put(StudentEntry.COLOUMN_NAME_GENDER, userDetails.getGenderIs());
        try {
            database.insert(StudentEntry.TABLE_NAME, null, content);
        } catch (SQLiteConstraintException e) {
            Log.d("TAG", "SQL exception caught");
        }
        database.close();
    }

    public ArrayList<UserDetails> retrieve() {
        ArrayList<UserDetails>queryDataList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+StudentEntry.TABLE_NAME+" ORDER BY "+StudentEntry.COLOUMN_NAME_NAME+" ASC";
        Cursor c =database.rawQuery(selectQuery,null);
        try {
            if (c.moveToFirst()) {
                do {
                    UserDetails userdata = new UserDetails(
                            c.getString(c.getColumnIndex(StudentEntry.COLOUMN_NAME_ADDRESS)),
                            c.getString(c.getColumnIndex(StudentEntry.COLOUMN_NAME_NAME)),
                            c.getString(c.getColumnIndex(StudentEntry.COLOUMN_NAME_GENDER)),
                            c.getString(c.getColumnIndex(StudentEntry.COLOUMN_NAME_ROLL)));
                    queryDataList.add(userdata);
                } while (c.moveToNext());

            }
        }finally {
            c.close();
            database.close();
        }
        return queryDataList;

    }

}
