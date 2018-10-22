package daredevil.project.models.DTO;

import daredevil.project.models.Estates;
import daredevil.project.repositories.base.AddressRepository;

public class EstateDTO {
    private int estateid;
    private String estateName;
    private boolean occupied;
    private float price;
    private AddressDTO address;
    private AddressRepository addressRepository;

    public EstateDTO() {
    }

    public EstateDTO(int estateid, String estateName, boolean occupied, float price, AddressDTO address) {
        this.estateid = estateid;
        this.estateName = estateName;
        this.occupied = occupied;
        this.price = price;
        this.address = address;
    }

    public int getEstateid() {
        return estateid;
    }

    public void setEstateid(int estateid) {
        this.estateid = estateid;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public static EstateDTO getFromEstate(Estates estates){
        return new EstateDTO(estates.getId(), estates.getEstateName(), estates.isOccupied(), estates.getPrice(), AddressDTO.getFromAddress(estates.getAddresses()));
    }
}
