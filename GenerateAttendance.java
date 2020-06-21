package com.example.schoolahapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GenerateAttendance extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String DATE = "date";
    public static final String ID = "id";
    public static final String COCU = "cocu";

    private TextView tvdate;
    private Spinner chooseCocu;
    private Button submitkehadiran;
    private ListView listkehadiran;

    private List<Attendance> attendanceList;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                attendanceList.clear();
                for (DataSnapshot attendanceSnapshot : dataSnapshot.getChildren()) {
                    Attendance attendance = attendanceSnapshot.getValue(Attendance.class);

                    attendanceList.add(attendance);

                }

                AttendanceList adapter = new AttendanceList(GenerateAttendance.this, attendanceList);
                listkehadiran.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

    }

            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_attendance);

        databaseReference = FirebaseDatabase.getInstance().getReference("Attendance");
        tvdate = (TextView) findViewById(R.id.tvdate);
        chooseCocu = (Spinner) findViewById(R.id.chooseCocu);
        listkehadiran = (ListView) findViewById(R.id.listkehadiran);
        submitkehadiran = (Button) findViewById(R.id.submitkehadiran);

        attendanceList = new ArrayList<>();
        submitkehadiran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAttendance();

            }
        });

        listkehadiran.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Attendance attendance = attendanceList.get(i);

                Intent intent = new Intent(getApplicationContext(),  AddAttendance.class);
                intent.putExtra(ID,attendance.getId());
                intent.putExtra(DATE,attendance.getDate());
                intent.putExtra(COCU, attendance.getCocu());

                startActivity(intent);

            }
        });


        
        Button button = (Button) findViewById(R.id.choose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.tvdate);
        textView.setText(currentDateString);

    }

    private void addAttendance() {
        String date = tvdate.getText().toString().trim();
        String cocu = chooseCocu.getSelectedItem().toString();

        if (!TextUtils.isEmpty(date) && (!TextUtils.isEmpty(cocu))) {

            String id = databaseReference.push().getKey();

            Attendance attendance = new Attendance (
                    id,
                    date,
                    cocu
            );

            databaseReference.child(id).setValue(attendance);

            Toast.makeText(this, "attendance added!!", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Enter Date !", Toast.LENGTH_LONG).show();
        }

    }




}
