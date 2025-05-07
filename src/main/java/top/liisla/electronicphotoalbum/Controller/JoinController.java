package top.liisla.electronicphotoalbum.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.ForgetPasswordEntityController;
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
    @PostMapping("/api/register")
    public CodeEntityReturn register(@RequestBody RegisterEntityController registerEntityController) {
        return joinService.register(registerEntityController);
    }

//    登录接口
    @PostMapping("/api/login")
    public CodeEntityReturn Login(@RequestBody LoginEntityController loginEntityController, HttpServletResponse response) {
        return joinService.login(loginEntityController, response);
    }

//    忘记密码接口
    @PostMapping("/api/forgetPassword")
    public CodeEntityReturn forgetPassword(@RequestBody ForgetPasswordEntityController forgetPasswordEntityController) {
        return joinService.ForgetPassword(forgetPasswordEntityController);
    }

//    获取修改密码时的验证码,这只是一个Demo作为测试使用
    @PostMapping("/api/getForgetPasswordVerificationNumber")
    public CodeEntityReturn getForgetPasswordVerificationNumber(String newUserPassword, String userAccount) {
        return joinService.GetForgetPasswordVerificationNumber(newUserPassword,userAccount);
    }

}
