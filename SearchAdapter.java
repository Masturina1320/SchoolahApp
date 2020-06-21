package com.example.schoolahapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

     Context context;
     ArrayList<String> nameStudent;
     ArrayList<String> phoneStudent;


    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView tvname, tvphone;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.tvname);
            tvphone = (TextView) itemView.findViewById(R.id.tvphone);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> nameStudent, ArrayList<String> phoneStudent, ValueEventListener searchStudentInfo) {
        this.context = context;
        this.nameStudent = nameStudent;
        this.phoneStudent = phoneStudent;
    }


    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchlist, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.tvname.setText(nameStudent.get(position));
        holder.tvphone.setText(phoneStudent.get(position));

        holder.tvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Full Name Clicked !", LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return nameStudent.size();
    }

}
