package com.example.schoolahapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class RegisterStudent extends AppCompatActivity {


    private EditText nameStudent,phoneStudent;
    private RadioButton female, male;
    private Spinner spinnerkelas;
    private Button registerpelajar,updateprofile;
    private ImageView profileimage;

    String PROFILE_IMAGE_URL = null;
    int TAKE_IMAGE_CODE = 1001;

    DatabaseReference databaseReference;
    private String TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        databaseReference = FirebaseDatabase.getInstance().getReference("Student");

        nameStudent = (EditText) findViewById(R.id.namastudent);
        phoneStudent = (EditText) findViewById(R.id.phonestudent);
        female = (RadioButton) findViewById(R.id.female);
        male = (RadioButton) findViewById(R.id.male);
        spinnerkelas = (Spinner) findViewById(R.id. spinnerkelas);
        registerpelajar = (Button) findViewById(R.id.registerpelajar);
        profileimage = (ImageView) findViewById(R.id.profileimage);
    //    updateprofile = (Button) findViewById(R.id.update);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

     /*   if( user !=null){
            Log.d(TAG,"onCreate" + user.getDisplayName());
            if(user.getDisplayName() !=null){
                nameStudent.setText(user.getDisplayName());
                nameStudent.setSelection(user.getDisplayName().length());

            }
            if(user.getPhotoUrl() !=null){
                Glide.with(this)
                        .load(user.getPhotoUrl())
                        .into(profileimage);

            }

        }*/

        registerpelajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStudent();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TAKE_IMAGE_CODE){
            switch (resultCode){
                case RESULT_OK:
                    Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                    profileimage.setImageBitmap(bitmap);
                    handleUpload(bitmap);
            }
        }
    }

    private void handleUpload(Bitmap bitmap){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final StorageReference reference= FirebaseStorage.getInstance().getReference()
                .child("profileImages")
                .child(uid+".jpeg");

        reference.putBytes(baos.toByteArray())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        getDownloadUrl(reference);


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure",e.getCause() );
                    }
                });
    }

    private void getDownloadUrl(StorageReference reference){
        reference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d(TAG, "onSuccess" + uri);

                    }
                });

    }

    private void setProfileimage(Uri uri)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();
        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegisterStudent.this,"Uploaded!" , Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterStudent.this, "Uploade failed !", Toast.LENGTH_SHORT).show();
                    }
                });


    }



    public void profileimage (View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(intent, TAKE_IMAGE_CODE);
        }


    }

    private void registerStudent(){
        String Username = nameStudent.getText().toString().trim();
        String Phone = phoneStudent.getText().toString().trim();
        String kelas = spinnerkelas.getSelectedItem().toString();
        String genderMale = male.getText().toString();
        String genderFemale = female.getText().toString();

        if(!TextUtils.isEmpty(Username)){
           String id = databaseReference.push().getKey();

            Student student = new Student (
                    id,
                    Username,
                    Phone,
                    kelas,
                    genderMale,
                    genderFemale
            );


           databaseReference.child(id).setValue(student);

            Toast.makeText(this, "Student Added!", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this,"Enter the details !",Toast.LENGTH_LONG).show();
        }
    }



}
