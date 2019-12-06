package model;

public class Business {
    private int id;
    private String name;
    private String description;
    private String zipCode;
    private String street;
    private String locality;
    private String posX;
    private String posY;
    private String phoneNumber;
    private int priceCategory;
    private boolean forMan;
    private boolean forWoman;
    private boolean forChild;
    private boolean online;

    // Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getLocality() {
        return locality;
    }

    public String getPosX() {
        return posX;
    }

    public String getPosY() {
        return posY;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPriceCategory() {
        return priceCategory;
    }

    public boolean isForMan() {
        return forMan;
    }

    public boolean isForWoman() {
        return forWoman;
    }

    public boolean isForChild() {
        return forChild;
    }

    public boolean isOnline() {
        return online;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPriceCategory(int priceCategory) {
        this.priceCategory = priceCategory;
    }

    public void setForMan(boolean forMan) {
        this.forMan = forMan;
    }

    public void setForWoman(boolean forWoman) {
        this.forWoman = forWoman;
    }

    public void setForChild(boolean forChild) {
        this.forChild = forChild;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }


}
