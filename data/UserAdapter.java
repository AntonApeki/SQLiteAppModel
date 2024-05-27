package com.example.sqliteappmodel.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.sqliteappmodel.R;

import java.util.List;
import java.util.zip.Inflater;

public class UserAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int layoutId;
    private List<User> users;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        layoutId = resource;
        users = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View view = inflater.inflate(layoutId, parent, false);
        TextView name;
        TextView number;

        name = view.findViewById(R.id.name);
        number = view.findViewById(R.id.number);

        User user = users.get(position);

        name.setText(user.name);
        number.setText(user.number);

        return view;

    }
}
