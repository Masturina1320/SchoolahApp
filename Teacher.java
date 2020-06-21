package com.example.schoolahapp;


public class Teacher  {


    public String coClass;
    public String teacherName;
    public String teacherId;

    public Teacher(){

    }
    public String getCoClass() {
        return coClass;
    }

    public void setCoClass(String coClass) {
        this.coClass = coClass;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Teacher(String id, String name, String cocurriculum) {
        this.teacherName = name;
        this.coClass = cocurriculum;
        this.teacherId = id;
    }

}








