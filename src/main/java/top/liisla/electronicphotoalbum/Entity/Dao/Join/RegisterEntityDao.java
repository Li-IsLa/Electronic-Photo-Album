package top.liisla.electronicphotoalbum.Entity.Dao.Join;

import lombok.Data;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.RegisterEntityController;

@Data
public class RegisterEntityDao {
    private String userName;
    private String userPassword;
    private String userPhoneNumber;
    private String userEmail;
    private String userGender;
    private String userPersonalSignature;
    private int userPower;

    public RegisterEntityDao(RegisterEntityController registerEntityController) {
        this.userName = registerEntityController.getUserName();
        this.userPassword = registerEntityController.getUserPassword();
        this.userPhoneNumber = registerEntityController.getUserPhoneNumber();
        this.userEmail = registerEntityController.getUserEmail();
        this.userGender = registerEntityController.getUserGender();
        this.userPersonalSignature = registerEntityController.getUserPersonalSignature();
        this.userPower = registerEntityController.getUserPower();
    }
}
