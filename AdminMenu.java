package com.example.schoolahapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminMenu extends AppCompatActivity {

     private CardView addStudent,addTeacher,generate,view,viewInfo,logout;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_admin_menu);
       addStudent = (CardView) findViewById(R.id.addstudent);
       addTeacher = (CardView) findViewById(R.id.addteacher);
       generate = (CardView) findViewById(R.id.generate);
       view = (CardView) findViewById(R.id.aboutcocu);
       viewInfo = (CardView) findViewById(R.id.schoolannouncement);
       logout = (CardView) findViewById(R.id.logout);


       addStudent.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in = new Intent(AdminMenu.this, AddRemoveStudent.class);
               startActivity(in);
           }
       });

       addTeacher.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in = new Intent(AdminMenu.this,AddRemoveTeacher.class);
               startActivity(in);
           }
       });



       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in = new Intent(AdminMenu.this,MainActivity.class);
               startActivity(in);

              /* AlertDialog.Builder builder = new AlertDialog.Builder(this)
               builder.setMessage("Confirm logout?")
                       .setCancelable(false)
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int i) {
                               Intent myIntent = new Intent(AdminMenu.this, MainActivity.class);
                               startActivity(myIntent);
                               finish();

                           }
                       })

                       .setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int which) {
                               dialogInterface.cancel();
                           }
                       });
               AlertDialog alertDialog = builder.create();
               alertDialog.show();*/

           }
       });


       generate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in = new Intent(AdminMenu.this, GenerateAttendance.class);
               startActivity(in);
           }
       });


       viewInfo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in = new Intent(AdminMenu.this,SearchStudentInfo.class);
               startActivity(in);
           }
       });
       /*
       addTeacher.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in = new Intent(AdminMenu.this,AddRemoveTeacher.class);
               startActivity(in);
           }
       });

       */

   }
}
