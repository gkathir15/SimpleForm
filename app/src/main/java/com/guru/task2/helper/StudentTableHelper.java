package com.guru.task2.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.guru.task2.constants.StudentListContract;
import com.guru.task2.data_model.UserDetails;

import java.util.ArrayList;

/**
 * Created by Guru on 02-03-2018.
 */

public class StudentTableHelper {

    long status = 0;
    UserDetails userDetails = new UserDetails();

    public void createStudentTable(SQLiteDatabase db)
    {
        String SQL_CREATE_STUDENT_DB = "CREATE TABLE" + StudentListContract.StudentEntry.TABLE_NAME
                + '(' + StudentListContract.StudentEntry.COLOUMN_NAME_ROLL + " INTEGER PRIMARY KEY NOT NULL,"
                + StudentListContract.StudentEntry.COLOUMN_NAME_NAME + " TEXT NOT NULL,"
                + StudentListContract.StudentEntry.COLOUMN_NAME_ADDRESS + " TEXT NOT NULL,"
                + StudentListContract.StudentEntry.COLOUMN_NAME_GENDER + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_STUDENT_DB);

    }


    public ArrayList<UserDetails> retrieveStudentData(SQLiteDatabase sqLiteDatabase) {
        ArrayList<UserDetails>queryDataList = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ StudentListContract.StudentEntry.TABLE_NAME+" ORDER BY "+ StudentListContract.StudentEntry.COLOUMN_NAME_NAME+" COLLATE NOCASE";
        Cursor c =sqLiteDatabase.rawQuery(selectQuery,null);
        try {
            if (c.moveToFirst()) {
                do {
                    UserDetails userdata = new UserDetails(
                            c.getString(c.getColumnIndex(StudentListContract.StudentEntry.COLOUMN_NAME_ADDRESS)),
                            c.getString(c.getColumnIndex(StudentListContract.StudentEntry.COLOUMN_NAME_NAME)),
                            c.getString(c.getColumnIndex(StudentListContract.StudentEntry.COLOUMN_NAME_GENDER)),
                            c.getString(c.getColumnIndex(StudentListContract.StudentEntry.COLOUMN_NAME_ROLL)));
                    queryDataList.add(userdata);
                } while (c.moveToNext());

            }
        }finally {
            c.close();
            sqLiteDatabase.close();
        }
        return queryDataList;

    }



    public long updateStudentTable(UserDetails userDetails, SQLiteDatabase sqLiteDatabase) {

        status=0;

        ContentValues content = new ContentValues();
        content.put(StudentListContract.StudentEntry.COLOUMN_NAME_ROLL, Integer.parseInt(userDetails.getRollNo()));
        content.put(StudentListContract.StudentEntry.COLOUMN_NAME_NAME, userDetails.getName());
        content.put(StudentListContract.StudentEntry.COLOUMN_NAME_ADDRESS, userDetails.getAddress());
        content.put(StudentListContract.StudentEntry.COLOUMN_NAME_GENDER, userDetails.getGenderIs());
        try{
            status =   sqLiteDatabase.insertOrThrow(StudentListContract.StudentEntry.TABLE_NAME, null, content);}
        catch (SQLiteConstraintException e){
            status =-1;
            Log.d("sql","Constaraint exception");
            Log.d("TAG", "SQL exception caught");
        }

        finally {
            sqLiteDatabase.close();
            Log.d("status", String.valueOf(status));
            // return -1;



        }
        return status;





    }

}







