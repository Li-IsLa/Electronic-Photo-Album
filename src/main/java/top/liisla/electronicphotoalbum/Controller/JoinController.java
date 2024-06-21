package top.liisla.electronicphotoalbum.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.LoginEntityController;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.RegisterEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.CodeEntityReturn;
import top.liisla.electronicphotoalbum.Service.Join.JoinService;

@RestController
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    //    注册接口
    @PostMapping("/register")
    public CodeEntityReturn register(RegisterEntityController registerEntityController) {
        return joinService.register(registerEntityController);
    }

//    登录接口
    @PostMapping("/login")
    public CodeEntityReturn Login(@RequestBody LoginEntityController loginEntityController, HttpServletResponse response) {
        return joinService.login(loginEntityController, response);
    }

}
