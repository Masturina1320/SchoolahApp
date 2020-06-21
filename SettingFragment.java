package com.example.schoolahapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

    public class SettingFragment extends Fragment {

        private Button ChangeProfile;


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment2, container, false);
            ChangeProfile = (Button) view.findViewById(R.id.btn_EditProfile);

            ChangeProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getActivity(),EditProfile.class);
                    startActivity(in);
                }
            });

            return view;

        }
}