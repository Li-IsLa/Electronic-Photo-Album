package top.liisla.electronicphotoalbum.Service.Join.Impl;

import org.springframework.stereotype.Service;
import top.liisla.electronicphotoalbum.Dao.Join.JoinDao;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.JoinEntityController;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.JoinEntityDao;
import top.liisla.electronicphotoalbum.Entity.Return.CodeEntityReturn;
import top.liisla.electronicphotoalbum.Rely.GetSha256;
import top.liisla.electronicphotoalbum.Service.Join.JoinService;

@Service
public class JoinServiceImpl implements JoinService {

    private final JoinDao joinDao;
    private final GetSha256 getSha256;

    public JoinServiceImpl(JoinDao joinDao, GetSha256 getSha256) {
        this.joinDao = joinDao;
        this.getSha256 = getSha256;
    }

    @Override
    public CodeEntityReturn register(JoinEntityController joinEntityController) {
        joinEntityController.setUserPassword(getSha256.getSHA256Key(joinEntityController.getUserPassword()));
        JoinEntityDao joinEntityDao = new JoinEntityDao(joinEntityController);
        CodeEntityReturn codeEntityReturn = new CodeEntityReturn();
//        我们会验证注册使用的手机号和邮箱是否重复
        if (joinDao.registrationCheck(joinEntityDao) && joinDao.registrationInsert(joinEntityDao)) {
            codeEntityReturn.setCode(201);
            codeEntityReturn.setMessage("注册成功请前往登录");
        } else {
            codeEntityReturn.setCode(400);
            codeEntityReturn.setMessage("电话号码或邮箱重复");
        }
        return codeEntityReturn;
    }
}
