package com.example.schoolahapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class EditProfile extends AppCompatActivity {


    private EditText profileName, profileEmail, profilePhone;
    private Button profileUpdate, profileView;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        profileName = (EditText) findViewById(R.id.Username2);
        profileEmail = (EditText) findViewById(R.id.email2);
        profilePhone = (EditText) findViewById(R.id.phoneNumber2);
        profileView = (Button) findViewById(R.id.saveBtn);
        profileUpdate = (Button) findViewById(R.id.button3);


        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.child("name").getValue().toString();
                        String phone = dataSnapshot.child("phone").getValue().toString();
                        String email = dataSnapshot.child("email").getValue().toString();


                        profileName.setText(name);
                        profileEmail.setText(email);
                        profilePhone.setText(phone);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Parents").child("7cvWmI5JDgW5MWkjG8PYoWALiDe2");
                String name, phone, email;

                name = profileName.getText().toString();
                phone = profilePhone.getText().toString();
                email = profileEmail.getText().toString();

                UserProfile userProfile = new UserProfile(name, phone, email);
                databaseReference.setValue(userProfile);
                Toast.makeText(EditProfile.this, "Updated !", Toast.LENGTH_SHORT).show();
            }
        });


    }
}

