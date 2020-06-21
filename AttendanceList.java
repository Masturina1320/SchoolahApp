package com.example.schoolahapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AttendanceList extends ArrayAdapter<Attendance> {

    private Activity context;
    private List<Attendance> attendanceList;

    public AttendanceList(Activity context, List<Attendance> attendanceList) {
        super(context, R.layout.activity_attendance_list, attendanceList);
        this.context = context;
        this.attendanceList = attendanceList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_attendance_list,null,true);

        TextView datetextview = (TextView) listViewItem.findViewById(R.id.datetextview);
        TextView cocutextview = (TextView) listViewItem.findViewById(R.id.cocutextview);

        Attendance attendance = attendanceList.get(position);

        datetextview.setText(attendance.getDate());
        cocutextview.setText(attendance.getCocu());

        return listViewItem;
    }
}
