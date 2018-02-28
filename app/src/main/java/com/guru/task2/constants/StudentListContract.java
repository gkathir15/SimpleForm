package com.guru.task2.constants;

import android.provider.BaseColumns;

/**
 * Created by User on 23-02-2018.
 */

public final class StudentListContract {

    private StudentListContract() {

    }
    public static class StudentEntry implements BaseColumns {

        public static final String TABLE_NAME=" studentsList";
        public static final String COLOUMN_NAME_ROLL="roll";
        public static final String COLOUMN_NAME_NAME="name";
        public static final String COLOUMN_NAME_GENDER="gender";
        public static final String COLOUMN_NAME_ADDRESS="address";
        public static String DATABASE_NAME = "StudentsDB";


    }
}
