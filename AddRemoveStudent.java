package com.example.schoolahapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class AddRemoveStudent extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String COCU = "cocu";

    private EditText Namestudent;
    private Spinner spinnerKoku,myspinner;
    private Button buttonadd;
    private ListView listpelajar;
    private List<StudentCocu> studentList;

    List<String> names;


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ArrayList<String> arrayList = new ArrayList<>();


    FirebaseDatabase firebaseDatabase;
  //  DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_remove_student);

        databaseReference = FirebaseDatabase.getInstance().getReference("New Student");
        listpelajar = (ListView) findViewById(R.id.listpelajar);
        Namestudent = (EditText) findViewById(R.id.namaPelajar);
        buttonadd = (Button) findViewById(R.id.addpelajar);
        spinnerKoku = (Spinner) findViewById(R.id.kelaskoku);
        myspinner = (Spinner) findViewById(R.id.myspinner);




        //   buttonremove = (Button)findViewById(R.id.removepelajar);

               showDataSpinner();

        studentList = new ArrayList<>();
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();

            }
        });

        listpelajar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                StudentCocu studentCocu = studentList.get(i);

                showUpdateDialog(studentCocu.getStudentId(), studentCocu.getStudentName());
                return false;
            }
        });





    }
    private void showUpdateDialog(final String studentId, final String studentName){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.updatedialog2, null);
        dialogBuilder.setView(dialogView);

        final EditText editText1 = (EditText) dialogView.findViewById(R.id.editText);
       // final TextView textView8 = (TextView) dialogView.findViewById(R.id.textView8);
        final Button button5 = (Button) dialogView.findViewById(R.id.button5);
        final Spinner spinner2 = (Spinner) dialogView.findViewById(R.id.spinner2);
        final Button button6 = (Button) dialogView. findViewById(R.id.button6);

        dialogBuilder.setTitle(""+ studentName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText1.getText().toString().trim();
                String cocu = spinner2.getSelectedItem().toString();

                if(TextUtils.isEmpty(name)){
                    editText1.setError("Name required");
                    return;

                }

                updateStudent(studentId,name, cocu);

                alertDialog.dismiss();


            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent(studentId);
            }
        });



    }

    private void deleteStudent(String studentId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("New Student").child(studentId);
        databaseReference.removeValue();

        Toast.makeText(this, "Student deleted!",Toast.LENGTH_LONG).show();

    }

    private boolean updateStudent(String studentId, String studentName, String coClass){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("New Student").child(studentId);

        StudentCocu studentCocu = new StudentCocu(studentId, studentName, coClass);

        databaseReference.setValue(studentCocu);
        Toast.makeText(this, "Student Updated!", Toast.LENGTH_LONG).show();

        return true;

    }



    private void showDataSpinner() {
        databaseReference.child("Student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();

                for(DataSnapshot item : dataSnapshot.getChildren()){
                    arrayList.add(item.child("name").getValue(String.class));
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddRemoveStudent.this, R.layout.style_spinner,arrayList);
                myspinner.setAdapter(arrayAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                studentList.clear();
                for(DataSnapshot studentSnapshot:dataSnapshot.getChildren()){
                    StudentCocu studentCocu = studentSnapshot.getValue(StudentCocu.class);

                    studentList.add(studentCocu);

                }

                StudentList adapter = new StudentList(AddRemoveStudent.this,studentList);
                listpelajar.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addStudent() {
        String student = Namestudent.getText().toString().trim();
        String cocurriculum = spinnerKoku.getSelectedItem().toString();

        if (!TextUtils.isEmpty(student)) {

            String id = databaseReference.push().getKey();

            StudentCocu studentCocu = new StudentCocu (
                    id,
                    student,
                    cocurriculum
            );

            databaseReference.child(id).setValue(studentCocu);

            Toast.makeText(this, "Student Added!", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show();
        }

    }


}



