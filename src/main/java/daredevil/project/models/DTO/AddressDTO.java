package daredevil.project.models.DTO;

import daredevil.project.models.Address;

public class AddressDTO {
    private int id;
    private String city;
    private String country;
    private char entrance;
    private int flat;
    private int floor;
    private String street;
    private int streetNumber;

    public AddressDTO() {
    }

    public AddressDTO(int id, String city, String country, char entrance, int flat, int floor, String street, int streetNumber) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.entrance = entrance;
        this.flat = flat;
        this.floor = floor;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public char getEntrance() {
        return entrance;
    }

    public void setEntrance(char entrance) {
        this.entrance = entrance;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public static AddressDTO getFromAddress(Address address){
        return new AddressDTO(address.getId(), address.getCity(), address.getCountry(), address.getEntrance(), address.getFlat(), address.getFloor(), address.getStreet(), address.getStreetNumber());
    }
}
