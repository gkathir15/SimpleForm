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

    long status = 0;
    StudentTableHelper studentTableHelper = new StudentTableHelper();


    public StudentDBHelper(Context context) {
        super(context, StudentEntry.DATABASE_NAME, null, DATABASE_VERSION);
    }




    public static int DATABASE_VERSION = 1;


    @Override
    public void onCreate(SQLiteDatabase db) {

        studentTableHelper.createStudentTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public ArrayList<UserDetails> retrieve() {
        ArrayList<UserDetails>queryDataList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+StudentEntry.TABLE_NAME+" ORDER BY "+StudentEntry.COLOUMN_NAME_NAME+" COLLATE NOCASE";
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
