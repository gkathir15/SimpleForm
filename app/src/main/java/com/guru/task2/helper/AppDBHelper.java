package com.guru.task2.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guru.task2.data_model.UserDetails;

import java.util.ArrayList;

import static com.guru.task2.constants.StudentListContract.*;

/**
 * Created by User on 23-02-2018.
 */

public class AppDBHelper extends SQLiteOpenHelper {


    StudentTableHelper studentTableHelper = new StudentTableHelper();


    public AppDBHelper(Context context) {
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

    public long updateDB(UserDetails userDetails) {


        SQLiteDatabase database = getWritableDatabase();

        return studentTableHelper.updateStudentTable(userDetails,database);





    }

    public ArrayList<UserDetails> retrieve() {
        SQLiteDatabase database = this.getReadableDatabase();

        return studentTableHelper.retrieveStudentData(database);

    }

}
