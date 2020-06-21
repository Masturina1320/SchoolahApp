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

public class StudentList extends ArrayAdapter<StudentCocu>  {

    private Activity context;
    private List<StudentCocu> studentList;

    public StudentList(Activity context, List<StudentCocu> studentList){
        super(context, R.layout.listlayout,studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.listlayout,null,true);

        TextView textViewname = (TextView) listViewItem.findViewById(R.id.tvname);
        TextView textViewkoku = (TextView) listViewItem.findViewById(R.id.textViewkoku);

        StudentCocu studentCocu = studentList.get(position);

        textViewname.setText(studentCocu.getStudentName());
        textViewkoku.setText(studentCocu.getCoClass());

        return listViewItem;
    }




}
