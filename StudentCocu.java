package com.example.schoolahapp;

import java.sql.Array;

public class StudentCocu {


    public String coClass;
    public String studentName;
    private String studentId;


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public StudentCocu() {

    }

    public StudentCocu(String id, String student, String cocurriculum) {
        this.studentName = student;
        this.coClass = cocurriculum;
        this.studentId = id;
    }


    public String getCoClass() {
        return coClass;
    }

    public void setCoClass(String coClass) {
        this.coClass = coClass;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }




}
