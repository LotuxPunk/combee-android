package com.example.combee.model;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static ArrayList<User> createUserList(int num) {
        ArrayList<User> users = new ArrayList<>();

        for(int i = 0; i < num; i++)
            users.add(new User("Clément", "Janssens"));

        return users;
    }
}
