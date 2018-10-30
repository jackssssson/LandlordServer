package daredevil.project.models.DTO;

import daredevil.project.models.Estates;

import java.util.Date;

public class EstateDTO {
    private int estateid;
    private String estateName;
    private boolean occupied;
    private float price;
    private String address;
    private String duedate;

    public EstateDTO() {
    }

    public EstateDTO(int estateid, String estateName, boolean occupied, float price, String address, String duedate) {
        this.estateid = estateid;
        this.estateName = estateName;
        this.occupied = occupied;
        this.price = price;
        this.address = address;
        this.duedate = duedate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public static EstateDTO getFromEstate(Estates estates){
        String dueDate=estates.getDueDate().toString();
        if(dueDate.equals("Mon Jan 11 00:11:00 EET 1999"))
            dueDate="";
        return new EstateDTO(estates.getId(), estates.getEstateName(), estates.isOccupied(), estates.getPrice(),  estates.getAddresses(), dueDate);
    }
}
