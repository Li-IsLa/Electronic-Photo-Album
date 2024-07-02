package top.liisla.electronicphotoalbum.Entity.Contorller.Join;

import lombok.Data;

@Data
public class ForgetPasswordEntityController {
    private String userAccount;
    private String newUserPassword;
    private String verificationNumber;
}
