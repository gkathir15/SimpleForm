package com.guru.task2.helper;

import android.database.sqlite.SQLiteDatabase;

import com.guru.task2.constants.StudentListContract;

/**
 * Created by Guru on 02-03-2018.
 */

public class StudentTableHelper {

    public void createStudentTable(SQLiteDatabase db)
    {
        String SQL_CREATE_STUDENT_DB = "CREATE TABLE" + StudentListContract.StudentEntry.TABLE_NAME
                + '(' + StudentListContract.StudentEntry.COLOUMN_NAME_ROLL + " INTEGER PRIMARY KEY NOT NULL,"
                + StudentListContract.StudentEntry.COLOUMN_NAME_NAME + " TEXT NOT NULL,"
                + StudentListContract.StudentEntry.COLOUMN_NAME_ADDRESS + " TEXT NOT NULL,"
                + StudentListContract.StudentEntry.COLOUMN_NAME_GENDER + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_STUDENT_DB);

    }






}
