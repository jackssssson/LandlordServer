package daredevil.project.models.DTO;

import daredevil.project.models.BankAccount;
import daredevil.project.models.User;

public class UserDTO {
    private int userid;
    private String userEmail;
    private String userName;
    private String userPassword;
    private BankAccountDTO bankAccount;
    private EstateDTO estate;

    public UserDTO() {
    }

    public UserDTO(int userid, String userEmail, String userName, String userPassword, BankAccountDTO bankAccount, EstateDTO estate) {
        this.userid = userid;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.bankAccount = bankAccount;
        this.estate = estate;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public BankAccountDTO getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountDTO bankAccount) {
        this.bankAccount = bankAccount;
    }

    public EstateDTO getEstate() {
        return estate;
    }

    public void setEstate(EstateDTO estate) {
        this.estate = estate;
    }

    public static UserDTO getFromUser(User user){
        return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getPassword(), BankAccountDTO.getByBankAccount(user.getBank_account()), EstateDTO.getFromEstate(user.getEstates()));
    }
}
