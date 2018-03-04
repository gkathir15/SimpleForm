package com.guru.task2.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.view.ContextThemeWrapper;

import com.guru.task2.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Guru on 02-03-2018.
 */

public class SetThemeHelper {

   public static String THEME_ID = "currentThemeID";
   public static String pref_THEME = R.string.app_name+"ThemeData";
   private static SharedPreferences sharedPreferences;
   private static SharedPreferences.Editor editor;



    boolean selectedTheme(Context context)
    {
         sharedPreferences = context.getSharedPreferences(pref_THEME,MODE_PRIVATE);

        boolean currentTheme = sharedPreferences.getBoolean(THEME_ID,false);
        if(currentTheme)//true
        {
            return true;
        }
        else {
            return false;
        }


    }

    public void writePreferenceData(Context context)
    {

         sharedPreferences = context.getSharedPreferences(pref_THEME,MODE_PRIVATE);
        if(!sharedPreferences.contains(THEME_ID))
        {
             editor = sharedPreferences.edit();
            editor.putBoolean(THEME_ID,false);
            editor.apply();
        }

    }


    public void setCustomTheme(Context context)
    {
        if(selectedTheme(context)){
            context.setTheme(R.style.AppThemeDark);
        }
        else
        {
            context.setTheme(R.style.AppTheme);
        }



    }

    public void setThemePref(Context context, Activity activity)
    {
         sharedPreferences = context.getSharedPreferences(pref_THEME,MODE_PRIVATE);
         editor = sharedPreferences.edit();
        if(selectedTheme(context))
        {
            editor.putBoolean(THEME_ID,false);
            editor.apply();

        }
        else {
            editor.putBoolean(THEME_ID, true);
            editor.apply();
        }


        activity.recreate();






    }





}
