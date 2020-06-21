package com.example.schoolahapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity {

    private EditText Username, Password;
    private Button buttonLogin;
    private TextView attempt, register;
    private int counter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView ForgotPassword;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        attempt = (TextView) findViewById(R.id.attempt);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        register = (TextView) findViewById(R.id.registercard);
        ForgotPassword = (TextView) findViewById(R.id.forgot);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember", " ");
        if(checkbox.equals("true")){
            Intent intent = new Intent(MainActivity.this, MenuParent.class);
            startActivity(intent);

        }else if(checkbox.equals("false")){

        }

        attempt.setText("No of attempts remaining: 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();


        Username.addTextChangedListener(loginTextwatcher);
        Password.addTextChangedListener(loginTextwatcher);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(), Password.getText().toString());


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, registerAcc.class));
            }
        });

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember" , "true");
                    editor.apply();

                    Toast.makeText(MainActivity.this, "Checked", LENGTH_SHORT).show();

                }else if(!compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember" , "false");
                    editor.apply();

                    Toast.makeText(MainActivity.this, "UnChecked", LENGTH_SHORT).show();

                }
            }
        });



    }

    public void validate(final String Username, final String Password) {

        if ((Username.equals("Admin")) && (Password.equals("1234"))){
            Intent intent = new Intent(MainActivity.this,AdminMenu.class );
            Toast.makeText(MainActivity.this,"Login Successful !", LENGTH_SHORT).show();
            startActivity(intent);
        }//else
          //  Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

        progressDialog.setMessage("Now everything is in your hand");
        progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(Username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MenuParent.class));
                    } else
                    //    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    attempt.setText("No of attempts remaining:" + counter);

                    progressDialog.dismiss();
                    if (counter == 0) {
                        buttonLogin.setEnabled(false);
                    }



                }

            });




        }

        private TextWatcher loginTextwatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String usernameInput= Username.getText().toString().trim();
                String passwordInput= Password.getText().toString().trim();

                buttonLogin.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
















