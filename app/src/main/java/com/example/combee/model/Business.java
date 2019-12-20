package com.example.combee.model;

import java.util.ArrayList;

public class Business {
    private Integer id;
    private String name;
    private String locality;
    private String picture;
    private Double averageReview;

    public Business(Integer id, String name, String locality, String picture, Double averageReview) {
        this.id = id;
        this.name = name;
        this.locality = locality;
        this.picture = picture;
        this.averageReview = averageReview;
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

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getAverageReview() {
        return averageReview;
    }

    public void setAverageReview(Double averageReview) {
        this.averageReview = averageReview;
    }
}
