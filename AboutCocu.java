package com.example.schoolahapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class AboutCocu extends AppCompatActivity {

    private Button bsm,krs,pp,pengakap,pi;
    private RecyclerView rvBsm;
    private List<StudentCocu> studentList;
   // private CocuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_cocu);


        bsm = (Button) findViewById(R.id.bsm);
        krs = (Button) findViewById(R.id.krs);
        pp = (Button) findViewById(R.id.pp);
        pengakap = (Button) findViewById(R.id.pengakap);
        pi = (Button) findViewById(R.id.pi);

        rvBsm = (RecyclerView) findViewById(R.id.rvBsm);
        rvBsm.setHasFixedSize(true);
        rvBsm.setLayoutManager(new LinearLayoutManager(this));
        studentList = new ArrayList<>();
     //   adapter = new
    }







}
