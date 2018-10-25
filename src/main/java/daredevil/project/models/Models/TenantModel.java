package daredevil.project.models.Models;

public class TenantModel {
    private String userName;
    private String userPassword;
    private String userEmail;
    private String iban;
    private String landlordName;

    public TenantModel(String userName, String userPassword, String userEmail, String iban, String landlordName) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.iban = iban;
        this.landlordName = landlordName;
    }

    public TenantModel() {
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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public void setLandlordName(String landlordName) {
        this.landlordName = landlordName;
    }
}
