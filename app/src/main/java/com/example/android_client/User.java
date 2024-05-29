package com.example.android_client;

public class User {
    private int id;
    private String name;
    private String nim;
    private String prodi;
    private String semester;
    private String email;
    public User(String name, String nim, String prodi, String semester, String email) {
        this.name = name;
        this.nim = nim;
        this.prodi = prodi;
        this.semester = semester;
        this.email = email; }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getNim() {return nim; }
    public String getProdi() {return prodi; }
    public String getSemester() {return semester; }
    public String getEmail() { return email; }
}
