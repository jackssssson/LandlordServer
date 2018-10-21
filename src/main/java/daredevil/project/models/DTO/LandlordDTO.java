package daredevil.project.models.DTO;

public class LandlordDTO {
    private String userName;
    private String userPassword;
    private String userEmail;
    private String city;
    private String country;
    private String street;
    private int flat;
    private int streetNumber;
    private int floor;
    private char entrance;
    private float price;

    public LandlordDTO(String userName, String userPassword, String userEmail, String city, String country, String street, int flat, int streetNumber, int floor, char entrance, float price) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.city = city;
        this.country = country;
        this.street = street;
        this.flat = flat;
        this.streetNumber = streetNumber;
        this.floor=floor;
        this.entrance = entrance;
        this.price = price;
    }

    public LandlordDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public char getEntrance() {
        return entrance;
    }

    public void setEntrance(char entrance) {
        this.entrance = entrance;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor){
        this.floor=floor;
    }
}
