package com.example.schoolahapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerAcc extends AppCompatActivity {

    private EditText username, email, parentPassword, phoneNumber;
    private Button signUpBtn;
    private TextView signedUp;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acc);

        username = (EditText) findViewById(R.id.Username);
        email = (EditText) findViewById(R.id.email);
        parentPassword = (EditText) findViewById(R.id.parentPassword);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        signedUp = (TextView) findViewById(R.id.signedUp);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);

        ref = FirebaseDatabase.getInstance().getReference("Parents");
        firebaseAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = username.getText().toString();
                final String Email = email.getText().toString();
                final String password = parentPassword.getText().toString();
                final String phone = phoneNumber.getText().toString();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(registerAcc.this, "Please enter email", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(registerAcc.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(registerAcc.this, "Please enter name", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(registerAcc.this, "Please enter phone", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.createUserWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(registerAcc.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    UserProfile userProfile = new UserProfile(
                                            name,
                                            Email,
                                            phone
                                    );

                                    FirebaseDatabase.getInstance().getReference("Parents")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(registerAcc.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        }
                                    });

                                } else {

                                    Toast.makeText(registerAcc.this, "Registration Failed", Toast.LENGTH_SHORT).show();


                                }


                            }
                        });
            }


        });
    }
}

