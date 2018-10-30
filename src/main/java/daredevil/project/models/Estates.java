package daredevil.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SimpleTimeZone;

@Entity
@Table(name = "estates")
public class Estates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estateID")
    private int id;

    @Column(name = "estate_Price", nullable = false)
    private float price;

    @Column(name = "estate_name", unique = true)
    private String estateName;

    @Column(name = "occupied", nullable = false, columnDefinition = "boolean default true")
    private boolean occupied;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "address", unique = true)
    private String addresses;

    public Estates(float price, String estateName, boolean occupied, Date dueDate, String addresses) {
        this.price = price;
        this.estateName = estateName;
        this.occupied = occupied;
        this.dueDate = dueDate;
        this.addresses = addresses;
    }

    public Estates(float price, String estateName,  String dueDate,  String addresses) throws ParseException {
        this.price = price;
        this.estateName = estateName;
        this.occupied = false;
        this.dueDate = getDateFromString(dueDate);
        this.addresses = addresses;
    }

    public Estates(float price, String estateName, String addresses){
        this.price = price;
        this.estateName = estateName;
        this.occupied = false;
        this.dueDate = null;
        this.addresses = addresses;
    }

    public Estates() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
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

    public Date getDueDate() {
        if(dueDate==null) {
            try {
                return getDateFromString("11-11-1999");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setDate(String date) throws ParseException {
        this.dueDate=getDateFromString(date);
    }
    private Date getDateFromString(String date) throws ParseException {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-mm-yyyy");
        return dt1.parse(date);

    }

}
