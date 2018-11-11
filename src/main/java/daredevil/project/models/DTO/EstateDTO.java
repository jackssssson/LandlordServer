package daredevil.project.models.DTO;

import daredevil.project.models.Estates;

public class EstateDTO {
    private int estateid;
    private String estateName;
    private boolean occupied;
    private float price;
    private String address;
    private String dueDate;

    public EstateDTO() {
    }

    public EstateDTO(int estateid, String estateName, boolean occupied, float price, String address, String dueDate) {
        this.estateid = estateid;
        this.estateName = estateName;
        this.occupied = occupied;
        this.price = price;
        this.address = address;
        this.dueDate = dueDate;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public static EstateDTO getFromEstate(Estates estates){
        String dueDate=estates.getDueDate().toString();
        if(dueDate.equals("Mon Jan 11 00:00:00 EET 1999"))
            dueDate="";
        return new EstateDTO(estates.getId(), estates.getEstateName(), estates.isOccupied(), estates.getPrice(),  estates.getAddresses(), dueDate);
    }
}
