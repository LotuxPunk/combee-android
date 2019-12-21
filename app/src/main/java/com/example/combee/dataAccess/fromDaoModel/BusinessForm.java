package com.example.combee.dataAccess.fromDaoModel;

public class BusinessForm {
    private Boolean isForMan;
    private Boolean isForWoman;
    private Boolean isForChild;

    private Boolean isPriceOne;
    private Boolean isPriceTwo;
    private Boolean isPriceThree;

    public BusinessForm() {
    }

    public Boolean getForMan() {
        return isForMan;
    }

    public void setForMan(Boolean forMan) {
        isForMan = forMan;
    }

    public Boolean getForWoman() {
        return isForWoman;
    }

    public void setForWoman(Boolean forWoman) {
        isForWoman = forWoman;
    }

    public Boolean getForChild() {
        return isForChild;
    }

    public void setForChild(Boolean forChild) {
        isForChild = forChild;
    }

    public Boolean getPriceOne() {
        return isPriceOne;
    }

    public void setPriceOne(Boolean priceOne) {
        isPriceOne = priceOne;
    }

    public Boolean getPriceTwo() {
        return isPriceTwo;
    }

    public void setPriceTwo(Boolean priceTwo) {
        isPriceTwo = priceTwo;
    }

    public Boolean getPriceThree() {
        return isPriceThree;
    }

    public void setPriceThree(Boolean priceThree) {
        isPriceThree = priceThree;
    }
}
