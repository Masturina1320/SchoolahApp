package com.example.schoolahapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportAttendance extends AppCompatActivity {

    private TextView textView32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_attendance);

        textView32 = (TextView) findViewById(R.id.textView32);


        textView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(textView32);
            }
        });
    }

    private void showDate(final TextView textView32) {

            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyy");
                    textView32.setText(simpleDateFormat.format(calendar.getTime()));

                }

            };

         new DatePickerDialog(ReportAttendance.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }



    public void export(View view) {
        //generate data
        StringBuilder data = new StringBuilder();
        data.append("Name,Class,Date,Time");
        data.append("\n"+"Azhar Bin Sulaiman"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Masturina Binti Mazelan"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Nur Ilyana Farah Binti Mohamad"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Mohamad Muhaimin Bin Mazelan"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Mohamad Muzakkir Bin Mazelan"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Nur Raisya Zahra Bin Zahid"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Ahmad Zainudin Bin Ali"  + "," + "Bulan Sabit Merah" + ","+ " ");
        data.append("\n"+"Izara Aisyah Binti Zaki"  + "," + "Bulan Sabit Merah" + ","+ " ");
        data.append("\n"+"Siti Sofia Bt Shukor"  + "," + "Bulan Sabit Merah" + ","+ " ");
        data.append("\n"+"Fatin Nazirah Bt Zulkefli"  + "," + "Bulan Sabit Merah" + ","+ " ");
        data.append("\n"+"Fatin Nazurah Bt Zulkefli"  + "," + "Bulan Sabit Merah" + ","+ " ");
        data.append("\n"+"Fatin Syazreen Bt Mohd Shuayli"  + "," + "Bulan Sabit Merah" + ","+ " ");
        data.append("\n"+"Irfan Shahir Bt Sazali"  + "," + "Bulan Sabit Merah" + ","+ " ");
        data.append("\n"+"Siti Halimah Bt Yusri"  + "," + "Bulan Sabit Merah" + ","+ " ");



        for (int i = 0; i < 5; i++) {
          //  data.append("\n" + String.valueOf(i) + "," + String.valueOf(i * i));
        }

        try {
            //saving the file into device
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write((data.toString()).getBytes());
            out.close();

            //exporting
            Context context = getApplicationContext();
            File filelocation = new File(getFilesDir(), "data.csv");
            Uri path = FileProvider.getUriForFile(context, "com.example.schoolahapp.fileprovider", filelocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(fileIntent, "Send mail"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void export1(View view) {
        //generate data
        StringBuilder data = new StringBuilder();
        data.append("Time,Distance");
        for (int i = 0; i < 5; i++) {
            data.append("\n" + String.valueOf(i) + "," + String.valueOf(i * i));
        }

        try {
            //saving the file into device
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write((data.toString()).getBytes());
            out.close();

            //exporting
            Context context = getApplicationContext();
            File filelocation = new File(getFilesDir(), "data.csv");
            Uri path = FileProvider.getUriForFile(context, "com.example.schoolahapp.fileprovider", filelocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(fileIntent, "Send mail"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void export2(View view) {
        //generate data
        StringBuilder data = new StringBuilder();
        data.append("Time,Distance");
        for (int i = 0; i < 5; i++) {
            data.append("\n" + String.valueOf(i) + "," + String.valueOf(i * i));
        }

        try {
            //saving the file into device
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write((data.toString()).getBytes());
            out.close();

            //exporting
            Context context = getApplicationContext();
            File filelocation = new File(getFilesDir(), "data.csv");
            Uri path = FileProvider.getUriForFile(context, "com.example.schoolahapp.fileprovider", filelocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(fileIntent, "Send mail"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void export3(View view) {
        //generate data
        StringBuilder data = new StringBuilder();
        data.append("Name,Class,Date,Time");
        data.append("\n"+"Mohamad Adzham Hafeez"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Mohamad Adzham Hafeez"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Mohamad Adzham Hafeez"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Mohamad Adzham Hafeez"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Mohamad Adzham Hafeez"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Mohamad Adzham Hafeez"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Mohamad Adzham Hafeez"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        data.append("\n"+"Mohamad Adzham Hafeez"  + "," + "Bulan Sabit Merah" + ","+ "Thursday 25-June-2020,11:07:20 AM");
        for (int i = 0; i < 5; i++) {

        }

        try {
            //saving the file into device
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write((data.toString()).getBytes());
            out.close();

            //exporting
            Context context = getApplicationContext();
            File filelocation = new File(getFilesDir(), "data.csv");
            Uri path = FileProvider.getUriForFile(context, "com.example.schoolahapp.fileprovider", filelocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(fileIntent, "Send mail"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void export4(View view) {
        //generate data
        StringBuilder data = new StringBuilder();
        data.append("Time,Distance");
        for (int i = 0; i < 5; i++) {
            data.append("\n" + String.valueOf(i) + "," + String.valueOf(i * i));
        }

        try {
            //saving the file into device
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write((data.toString()).getBytes());
            out.close();

            //exporting
            Context context = getApplicationContext();
            File filelocation = new File(getFilesDir(), "data.csv");
            Uri path = FileProvider.getUriForFile(context, "com.example.schoolahapp.fileprovider", filelocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(fileIntent, "Send mail"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

