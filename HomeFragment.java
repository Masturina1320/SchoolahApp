package com.example.schoolahapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {

    private CardView Register, Update, Cocurriculum, Event;



    @Nullable
    @Override
       public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment1, container, false);

           Register = (CardView) view.findViewById(R.id.studentprofile);
          // Cocurriculum = (CardView) view.findViewById(R.id.cocurriculumcard);
         // Event = (CardView) view.findViewById(R.id.eventcard);



        Register.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
              Intent in = new Intent(getActivity(),RegisterStudent.class);
                startActivity(in);
            }
        });



            return view;



       }



}
