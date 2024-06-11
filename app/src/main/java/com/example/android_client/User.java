package com.example.android_client;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("nim")
    private String nim;
    @SerializedName("prodi")
    private String prodi;
    @SerializedName("semester")
    private String semester;
    @SerializedName("email")
    private String email;

    public User(int id, String name, String nim, String prodi, String semester, String email) {
        this.id = id;
        this.name = name;
        this.nim = nim;
        this.prodi = prodi;
        this.semester = semester;
        this.email = email;
    }
    // Konstruktor untuk membuat objek User tanpa id (misalnya, untuk menambahkan user baru)
    public User(String name, String nim, String prodi, String semester, String email) {
        this.name = name;
        this.nim = nim;
        this.prodi = prodi;
        this.semester = semester;
        this.email = email; }

    // Getter untuk mendapatkan id user
    public int getId() {
        return id;
    }
    // Setter untuk mengatur id user
    public void setId(int id) {
        this.id = id;
    }
    // Getter untuk mendapatkan nama user
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }
    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}


