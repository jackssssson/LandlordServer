package daredevil.project.models.DTO;

import daredevil.project.models.Estates;
import daredevil.project.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private int userid;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userIban;
    private List<EstateDTO> estates;
    private String userType;
    private String userRating;

    public UserDTO() {
    }

    public UserDTO(int userid, String userEmail, String userName, String userPassword, String userIban) {
        this.userid = userid;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userIban=userIban;
        this.estates =new ArrayList<>();
        this.userRating="";
    }

    public UserDTO(int userid, String userEmail, String userName, String userPassword, String userIban, String userType) {
        this.userid = userid;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userIban=userIban;
        this.estates =new ArrayList<>();
        this.userType=userType;
        this.userRating="";
    }

    public UserDTO(int userid, String userEmail, String userName, String userPassword, String userIban,  String userType, String userRating) {
        this.userid = userid;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userIban = userIban;
        this.estates = new ArrayList<>();
        this.userType = userType;
        this.userRating = userRating;
    }

    public UserDTO(int userid, String userEmail, String userName, String userPassword, String userIban, List<EstateDTO> estates, String userType, String userRating) {
        this.userid = userid;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userIban = userIban;
        this.estates = estates;
        this.userType = userType;
        this.userRating = userRating;
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


    public List<EstateDTO> getEstates() {
        return estates;
    }

    public void setEstates(List<EstateDTO> estates) {
        this.estates = estates;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public static UserDTO getFromUser(User user){
        return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getIban(), getEstateDTOListFromEstateList(user.getEstates()), user.getUser_type(), user.getUserRating());
    }

    private static List<EstateDTO> getEstateDTOListFromEstateList(Set<Estates> estates){
        List<EstateDTO> result=new ArrayList<>();
        for(Estates e: estates){
            int b=5;
            result.add(EstateDTO.getFromEstate(e));
        }
        return result;
    }
}
