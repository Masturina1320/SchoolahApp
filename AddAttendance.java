package com.example.schoolahapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddAttendance extends AppCompatActivity {

    private EditText etname;
    private TextView dateTv;
    private TextView cocuTv;
    private Button button2;
    private ListView listViewAttendance;

    DatabaseReference databaseReference;
    private List<Kehadiran> kehadiranList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);
        kehadiranList = new ArrayList<>();

        dateTv = (TextView) findViewById(R.id.dateTv);
        cocuTv = (TextView) findViewById(R.id.cocuTv);
        etname = (EditText) findViewById(R.id.etname);
        button2 = (Button) findViewById(R.id.button2);
        listViewAttendance = (ListView) findViewById(R.id.listViewAttendance);

        Intent intent = getIntent();

        String id = intent.getStringExtra(GenerateAttendance.ID);
        String date = intent.getStringExtra(GenerateAttendance.DATE);
        String cocu = intent.getStringExtra(GenerateAttendance.COCU);

        dateTv.setText(date);
        cocuTv.setText(cocu);
   databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceList").child(id);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAttendance();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                kehadiranList.clear();
                for(DataSnapshot kehadiranSnapshot:dataSnapshot.getChildren()){
                    Kehadiran kehadiran = kehadiranSnapshot.getValue(Kehadiran.class);

                    kehadiranList.add(kehadiran);

                }

                KehadiranList adapter = new KehadiranList(AddAttendance.this,kehadiranList);
                listViewAttendance.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addAttendance(){
        String name = etname.getText().toString().trim();
         if(!TextUtils.isEmpty(name)){
             String id = databaseReference.push().getKey();

             Kehadiran kehadiran = new Kehadiran(id,name);
             databaseReference.child(id).setValue(kehadiran);

             Toast.makeText(this, "Name Added!", Toast.LENGTH_LONG).show();

         }else {
             Toast.makeText(this, "Enter name!", Toast.LENGTH_LONG).show();
         }

    }


    }

