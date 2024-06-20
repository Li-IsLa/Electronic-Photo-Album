package top.liisla.electronicphotoalbum.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.JoinEntityController;
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
    public CodeEntityReturn register(JoinEntityController joinEntityController) {
        return joinService.register(joinEntityController);
    }

}
