package com.example.schoolahapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class KehadiranList extends ArrayAdapter<Kehadiran> {
    private Activity context;
    private List<Kehadiran> kehadiranList;

    public KehadiranList(Activity context, List<Kehadiran> kehadiranList) {
        super(context, R.layout.kehadiran_list, kehadiranList);
        this.context = context;
        this.kehadiranList = kehadiranList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.kehadiran_list,null,true);

        TextView name = (TextView) listViewItem.findViewById(R.id.textView8);

        Kehadiran kehadiran = kehadiranList.get(position);

        name.setText(kehadiran.getName());


        return listViewItem;
    }


}
