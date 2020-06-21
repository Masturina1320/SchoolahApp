package com.example.schoolahapp;

public class Attendance {
    
    public String date;
    public String cocu;
    public String id;

    public Attendance(String id, String date, String cocu) {
        this.id = id;
        this.date = date;
        this.cocu = cocu;

    }
    public Attendance() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCocu() {
        return cocu;
    }

    public void setCocu(String cocu) {
        this.cocu = cocu;
    }
}
