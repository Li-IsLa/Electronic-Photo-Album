package top.liisla.electronicphotoalbum.Dao.Join;

import top.liisla.electronicphotoalbum.Entity.Contorller.Join.ForgetPasswordEntityController;
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
//    检查忘记密码验证码是否正确和是否在有效时间内
    boolean inspectForGetInfo(ForgetPasswordEntityController forgetPasswordEntityController);
//    修改忘记密码的密码
    boolean updateUserPasswordForForgetPassword(ForgetPasswordEntityController forgetPasswordEntityController);
//    添加ForgetPasswordKey
    boolean addForgetPasswordKey(Long timeStamp, String forgetPasswordKey, String userAssount);
}
