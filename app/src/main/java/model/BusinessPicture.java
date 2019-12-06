package model;

public class BusinessPicture {
    private int id;
    private Business business;
    private String link;

    // Getter
    public int getId() {
        return id;
    }

    public Business getBusiness() {
        return business;
    }

    public String getLink() {
        return link;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
