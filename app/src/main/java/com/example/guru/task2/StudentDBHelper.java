package com.example.guru.task2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.guru.task2.StudentListContract.*;

/**
 * Created by User on 23-02-2018.
 */

public class StudentDBHelper extends SQLiteOpenHelper {

    public StudentDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static String DATABASE_NAME = "StudentsDB";
    public static int DATABASE_VERSION = 1;



    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_STUDENT_DB = "CREATE TABLE" + StudentEntry.TABLE_NAME
                +'(' + StudentEntry.COLOUMN_NAME_ROLL + " INTEGER PRIMARY KEY,"
                + StudentEntry.COLOUMN_NAME_NAME + " TEXT,"
                + StudentEntry.COLOUMN_NAME_ADDRESS + " TEXT,"
                + StudentEntry.COLOUMN_NAME_GENDER + "TEXT);";

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
