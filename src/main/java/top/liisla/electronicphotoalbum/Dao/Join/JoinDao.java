package top.liisla.electronicphotoalbum.Dao.Join;

import top.liisla.electronicphotoalbum.Entity.Dao.Join.LoginEntityDaoInput;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.LoginEntityDaoOutpt;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.RegisterEntityDao;

public interface JoinDao {
//    注册检查
    boolean registrationCheck(RegisterEntityDao registerEntityDao);
//    注册添加
    boolean registrationInsert(RegisterEntityDao registerEntityDao);
//    登录查询
    LoginEntityDaoOutpt account(LoginEntityDaoInput loginEntityDaoInput);
//    添加userKey
    boolean addUserKey(LoginEntityDaoInput loginEntityDaoInput, String userKey);
}
