package com.example.schoolahapp;

public class Student {

    public String name;
    public String phone;
    public String idPelajar;
    public String kelas;
    public String gender;

    public Student(String id, String username, String phone, String kelas, String genderMale, String genderFemale) {

        this.name = username;
        this.phone = phone;
        this.kelas = kelas;
        this.gender = genderMale;
        this.gender = genderFemale;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getIdPelajar() {
        return idPelajar;
    }

    public void setIdPelajar(String idPelajar) {
        this.idPelajar = idPelajar;
    }
}