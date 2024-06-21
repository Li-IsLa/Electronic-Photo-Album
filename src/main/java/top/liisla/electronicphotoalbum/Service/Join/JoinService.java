package top.liisla.electronicphotoalbum.Service.Join;

import jakarta.servlet.http.HttpServletResponse;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.LoginEntityController;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.RegisterEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.CodeEntityReturn;

public interface JoinService {
//    注册实现
    CodeEntityReturn register(RegisterEntityController registerEntityController);
//    登录实现
    CodeEntityReturn login(LoginEntityController loginEntityController, HttpServletResponse response);
}
