package top.liisla.electronicphotoalbum.Entity.Dao.Join;

import lombok.Data;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.LoginEntityController;

@Data
public class LoginEntityDaoInput {
    private String userAccount;

    public LoginEntityDaoInput(LoginEntityController loginEntityController) {
        this.userAccount = loginEntityController.getUserAccount();
    }
}
