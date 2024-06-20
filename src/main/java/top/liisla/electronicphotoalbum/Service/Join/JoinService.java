package top.liisla.electronicphotoalbum.Service.Join;

import top.liisla.electronicphotoalbum.Entity.Contorller.Join.JoinEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.CodeEntityReturn;

public interface JoinService {
//    注册实现
    CodeEntityReturn register(JoinEntityController joinEntityController);
}
