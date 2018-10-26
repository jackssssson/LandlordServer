package daredevil.project.models.DTO;

import daredevil.project.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private int userid;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userIban;
    private List<EstateDTO> estateDTOS;

    public UserDTO() {
    }

    public UserDTO(int userid, String userEmail, String userName, String userPassword, String userIban) {
        this.userid = userid;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userIban=userIban;
        this.estateDTOS=new ArrayList<>();
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

    public String getUserIban() {
        return userIban;
    }

    public void setUserIban(String userIban) {
        this.userIban = userIban;
    }

    public static UserDTO getFromUser(User user){
        return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getIban());
    }

    public List<EstateDTO> getEstateDTOS() {
        return estateDTOS;
    }

    public void setEstateDTOS(List<EstateDTO> estateDTOS) {
        this.estateDTOS = estateDTOS;
    }
}
