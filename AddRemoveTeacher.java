package com.example.schoolahapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddRemoveTeacher extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String COCU = "cocu";

    private Spinner teacherName;
    private Spinner cocurriculumClass;
    private Button buttonadd, buttonremove;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Teacher> teacherList;
    private ListView listguru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_teacher);

        databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");
        teacherName = (Spinner) findViewById(R.id.Teachers);
        buttonadd = (Button) findViewById(R.id.tambahguru);
        cocurriculumClass = (Spinner) findViewById(R.id.spinnerkelaskoku);
        listguru = (ListView) findViewById(R.id.listguru);

        teacherList = new ArrayList<>();
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacher();

            }
        });


       listguru.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
               Teacher teacher = teacherList.get(i);

               showUpdateDialog(teacher.getTeacherName(), teacher.getTeacherId());
               return false;
           }
       });



    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                teacherList.clear();
                for(DataSnapshot teacherSnapshot:dataSnapshot.getChildren()){
                    Teacher teacher = teacherSnapshot.getValue(Teacher.class);

                   teacherList.add(teacher);

                }

                TeacherList adapter = new TeacherList(AddRemoveTeacher.this,teacherList);
                listguru.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDialog(final String teacherName, final String teacherId ){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText etUpdateName = (EditText) dialogView.findViewById(R.id.etUpdateName);
       // final TextView textViewname = (TextView) dialogView.findViewById(R.id.textViewName);
        final Button updateButton = (Button) dialogView.findViewById(R.id.button4);
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinnerUpdate);
        final Button deletebutton = (Button) dialogView. findViewById(R.id.deletebutton);

        dialogBuilder.setTitle(""+ teacherName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etUpdateName.getText().toString().trim();
                String cocu = spinner.getSelectedItem().toString();

                if(TextUtils.isEmpty(name)){
                    etUpdateName.setError("Name required");
                    return;

                }

                updateTeacher(teacherId,name, cocu);

                alertDialog.dismiss();


            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTeacher(teacherId);
            }
        });


    }

    private void deleteTeacher(String teacherId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Teacher").child(teacherId);
        databaseReference.removeValue();

        Toast.makeText(this, "Teacher deleted!",Toast.LENGTH_LONG).show();

    }

    private boolean updateTeacher(String teacherId, String teacherName, String coClass){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Teacher").child(teacherId);

        Teacher teacher = new Teacher(teacherId, teacherName, coClass);

        databaseReference.setValue(teacher);
        Toast.makeText(this, "Teacher Updated!", Toast.LENGTH_LONG).show();

        return true;


    }




    private void addTeacher(){
        String name = teacherName.getSelectedItem().toString();
        String cocurriculum = cocurriculumClass.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)) {

            String id = databaseReference.push().getKey();

            Teacher teacher = new Teacher(
                    id,
                    name,
                    cocurriculum
            );

            databaseReference.child(id).setValue(teacher);
            Toast.makeText(this,"Teacher Added!", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Enter Name!",Toast.LENGTH_LONG).show();
        }
    }


}

