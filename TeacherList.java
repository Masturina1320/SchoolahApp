package com.example.schoolahapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.schoolahapp.R;
import com.example.schoolahapp.StudentCocu;

import java.util.List;

public class TeacherList extends ArrayAdapter<Teacher> {

    private Activity context;
    private List<Teacher> teacherList;

    public TeacherList(Activity context,List<Teacher>teacherList){
        super(context, R.layout.listlayout2,teacherList);
        this.context = context;
        this.teacherList = teacherList;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.listlayout2,null,true);

        TextView textViewGuru = (TextView) listViewItem.findViewById(R.id.textViewGuru);
        TextView textViewCocu = (TextView) listViewItem.findViewById(R.id.textViewCocu);

        Teacher teacher = teacherList.get(position);

        textViewGuru.setText(teacher.getTeacherName());
        textViewCocu.setText(teacher.getCoClass());

        return listViewItem;
    }
}
