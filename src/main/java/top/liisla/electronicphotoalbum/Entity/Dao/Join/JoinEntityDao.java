package top.liisla.electronicphotoalbum.Entity.Dao.Join;

import lombok.Data;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.JoinEntityController;

@Data
public class JoinEntityDao {
    private String userName;
    private String userPassword;
    private String userPhoneNumber;
    private String userEmail;
    private String userGender;
    private String userPersonalSignature;

    public JoinEntityDao(JoinEntityController joinEntityController) {
        this.userName = joinEntityController.getUserName();
        this.userPassword = joinEntityController.getUserPassword();
        this.userPhoneNumber = joinEntityController.getUserPhoneNumber();
        this.userEmail = joinEntityController.getUserEmail();
        this.userGender = joinEntityController.getUserGender();
        this.userPersonalSignature = joinEntityController.getUserPersonalSignature();
    }
}
