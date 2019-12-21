package com.example.combee.model;

public class FullBusiness extends Business {
    private String description;
    private String street;
    private String zipCode;

    public FullBusiness(Integer id, String name, String locality, String picture, Double averageReview, Integer priceCategory, String description, String street, String zipCode) {
        super(id, name, locality, picture, averageReview, priceCategory);

        this.description = description;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
