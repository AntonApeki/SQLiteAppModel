package com.example.sqliteappmodel.data;

public class User {
    public long id;
    public String name;
    public String number;

    public User(long id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public User() {

    }
}
