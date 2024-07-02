package top.liisla.electronicphotoalbum.Service.Join.Impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import top.liisla.electronicphotoalbum.Dao.Join.JoinDao;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.ForgetPasswordEntityController;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.LoginEntityController;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.RegisterEntityController;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.LoginEntityDaoInput;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.LoginEntityDaoOutpt;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.RegisterEntityDao;
import top.liisla.electronicphotoalbum.Entity.Return.CodeEntityReturn;
import top.liisla.electronicphotoalbum.Rely.GetSha256;
import top.liisla.electronicphotoalbum.Rely.GetTimeStamp;
import top.liisla.electronicphotoalbum.Rely.GetUserLoginKey;
import top.liisla.electronicphotoalbum.Rely.SetCookie;
import top.liisla.electronicphotoalbum.Service.Join.JoinService;

@Service
public class JoinServiceImpl implements JoinService {

    private final JoinDao joinDao;
    private final GetSha256 getSha256;
    private final SetCookie setCookie;
    private final GetUserLoginKey getUserLoginKey;
    private final GetTimeStamp getTimeStamp;

    public JoinServiceImpl(JoinDao joinDao, GetSha256 getSha256, SetCookie setCookie, GetUserLoginKey getUserLoginKey, GetTimeStamp getTimeStamp) {
        this.joinDao = joinDao;
        this.getSha256 = getSha256;
        this.setCookie = setCookie;
        this.getUserLoginKey = getUserLoginKey;
        this.getTimeStamp = getTimeStamp;
    }

//    注册
    @Override
    public CodeEntityReturn register(RegisterEntityController registerEntityController) {
        registerEntityController.setUserPassword(getSha256.getSHA256Key(registerEntityController.getUserPassword()));
        RegisterEntityDao registerEntityDao = new RegisterEntityDao(registerEntityController);
        CodeEntityReturn codeEntityReturn = new CodeEntityReturn();
//        我们会验证注册使用的手机号和邮箱是否重复
        if (joinDao.registrationCheck(registerEntityDao) && joinDao.registrationInsert(registerEntityDao)) {
            codeEntityReturn.setCode(200);
            codeEntityReturn.setMessage("注册成功请前往登录");
        } else {
            codeEntityReturn.setCode(400);
            codeEntityReturn.setMessage("电话号码或邮箱重复");
        }
        return codeEntityReturn;
    }

//    登陆
    @Override
    public CodeEntityReturn login(LoginEntityController loginEntityController, HttpServletResponse response) {
//        设置加密后的sha256
        loginEntityController.setUserPassword(getSha256.getSHA256Key(loginEntityController.getUserPassword()));
//        转换实体
        LoginEntityDaoInput loginEntityDaoInput = new LoginEntityDaoInput(loginEntityController);
//        实例返回实体
        CodeEntityReturn codeEntityReturn = new CodeEntityReturn();
//        获取用户的实际密码
        LoginEntityDaoOutpt userInfo = joinDao.account(loginEntityDaoInput);
        if (userInfo != null && loginEntityController.getUserPassword().equals(userInfo.getUserPassword())) {
//            获取用户登录key
            String userKey = getUserLoginKey.getUserKey(loginEntityController.getUserAccount());
//            将用户的userKey保存到数据库
            if (joinDao.addUserKey(loginEntityDaoInput, userKey)) {
                codeEntityReturn.setCode(400);
                codeEntityReturn.setMessage("登录失败请稍后再尝试");
            }
            Cookie userKeyCookie = setCookie.createCookie("userKey", userKey , "app.li-isla.net", "/", loginEntityController.getLoginTime(), true);
            Cookie userNameCookie = setCookie.createCookie("userName", userInfo.getUserName(), "app.li-isla.net", "/", loginEntityController.getLoginTime(), true);
            response.addCookie(userKeyCookie);
            response.addCookie(userNameCookie);
            codeEntityReturn.setCode(200);
            codeEntityReturn.setMessage("登录成功");
        } else {
            codeEntityReturn.setCode(400);
            codeEntityReturn.setMessage("账号或密码错误");
        }
        return codeEntityReturn;
    }

//    忘记密码
    @Override
    public CodeEntityReturn ForgetPassword(ForgetPasswordEntityController forgetPasswordEntityController) {
        forgetPasswordEntityController.setNewUserPassword(getSha256.getSHA256Key(forgetPasswordEntityController.getNewUserPassword()));
        CodeEntityReturn codeEntityReturn = new CodeEntityReturn();
        if (joinDao.inspectForGetInfo(forgetPasswordEntityController) && joinDao.updateUserPasswordForForgetPassword(forgetPasswordEntityController)) {
            codeEntityReturn.setCode(200);
            codeEntityReturn.setMessage("修改成功");
        } else {
            codeEntityReturn.setCode(400);
            codeEntityReturn.setMessage("修改失败");
        }
        return codeEntityReturn;
    }

//    获取忘记密码验证码
    @Override
    public CodeEntityReturn GetForgetPasswordVerificationNumber(String newUserPassword, String userAccount) {
        long timeStampOfUTC8 = getTimeStamp.getTimeStampOfUTC8() +  300000;
        String sha256Key = getSha256.getSHA256Key(newUserPassword + timeStampOfUTC8).substring(0,8);
        CodeEntityReturn codeEntityReturn = new CodeEntityReturn();
        if (joinDao.addForgetPasswordKey(timeStampOfUTC8, sha256Key, userAccount)) {
            codeEntityReturn.setCode(200);
            codeEntityReturn.setMessage(sha256Key);
        } else {
            codeEntityReturn.setCode(400);
            codeEntityReturn.setMessage("获取失败");
        }
        return codeEntityReturn;
    }
}
