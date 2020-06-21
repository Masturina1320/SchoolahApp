package com.example.schoolahapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchStudentInfo extends AppCompatActivity {

    EditText searchStudent;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    ArrayList<String> nameStudent;
    ArrayList<String> phoneStudent;
    SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student_info);


        searchStudent = (EditText) findViewById(R.id.searchName);
        recyclerView = (RecyclerView) findViewById(R.id.recylerview);

        databaseReference = FirebaseDatabase.getInstance().getReference("Student");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        nameStudent = new ArrayList<>();
        phoneStudent = new ArrayList<>();

        searchStudent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());

                }else{
                    nameStudent.clear();
                    phoneStudent.clear();
                    recyclerView.removeAllViews();

                }
            }
        });


    }

    private void setAdapter (final String searchedString){
        nameStudent.clear();
        phoneStudent.clear();
        recyclerView.removeAllViews();

        databaseReference.child("Student");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nameStudent.clear();
                phoneStudent.clear();
                recyclerView.removeAllViews();

                int counter = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String Name = snapshot.child("name").getValue(String.class);
                    String Phone = snapshot.child("phone").getValue(String.class);

                    if (Name.toLowerCase().contains(searchedString.toLowerCase())) {

                        nameStudent.add(Name);
                        phoneStudent.add(Phone);
                        counter++;


                    } else if (Phone.toLowerCase().contains(searchedString.toLowerCase())) {
                        nameStudent.add(Name);
                        phoneStudent.add(Phone);
                        counter++;

                    }

                    if (counter == 15)
                        break;


                }


                searchAdapter = new SearchAdapter(SearchStudentInfo.this, nameStudent, phoneStudent,this);
                recyclerView.setAdapter(searchAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}


