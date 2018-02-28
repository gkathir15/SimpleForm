package com.guru.task2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.guru.task2.R;
import com.guru.task2.data_model.UserDetails;

import java.util.ArrayList;

/**
 * Created by User on 21-02-2018.
 */

public class ListAdapter extends ArrayAdapter<UserDetails> {


    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UserDetails> objects) {
        super(context, resource,objects);
    }

    public View getView(final int position, View listView, ViewGroup parentView)
    {
        Log.d("arrayAdapter","getView called");

        UserDetails user = getItem(position);
        if(listView ==null) {

        listView = LayoutInflater.from(getContext()).inflate(R.layout.roll_list, parentView, false);

        }
        TextView tV1= listView.findViewById(R.id.rollText);
        tV1.setText(user.getName());
        tV1.setTag(position);




        return listView;

    }
}
