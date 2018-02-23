package com.example.guru.task2;

import android.provider.BaseColumns;

/**
 * Created by User on 23-02-2018.
 */

public final class StudentListContract {

    private StudentListContract() {

    }
    public static class StudentEntry implements BaseColumns {

        public static final String TABLE_NAME="studentsList";
        public static final String COLOUMN_NAME_ROLL="userName";
        public static final String COLOUMN_NAME_NAME="address";
        public static final String COLOUMN_NAME_GENDER="gender";
        public static final String COLOUMN_NAME_ADDRESS="address";


    }
}
