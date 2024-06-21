package top.liisla.electronicphotoalbum.Entity.Contorller.Join;

import lombok.Data;

@Data
public class LoginEntityController {
    private String userAccount;
    private String userPassword;
    private int loginTime;
}
