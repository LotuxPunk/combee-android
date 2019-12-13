package com.example.combee.model;

import java.util.ArrayList;

public class Business {
    private Integer id;
    private String name;
    private String description;
    private String zipCode;
    private String street;
    private String locality;
    private String phoneNumber;

    public Business(Integer id, String name, String description, String zipCode, String street, String locality, Double posX, Double posY, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.zipCode = zipCode;
        this.street = street;
        this.locality = locality;
        this.phoneNumber = phoneNumber;
    }


    public static ArrayList<Business> getAll() {
        ArrayList<Business> businesses = new ArrayList<>();

        return businesses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
