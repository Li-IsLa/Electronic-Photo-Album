package top.liisla.electronicphotoalbum.Dao.Join.Impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import top.liisla.electronicphotoalbum.Dao.Join.JoinDao;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.LoginEntityDaoInput;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.LoginEntityDaoOutpt;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.RegisterEntityDao;
import top.liisla.electronicphotoalbum.Mapper.JoinMapper;

@Service
public class JoinDaoImpl implements JoinDao {

//    注入SqlSessionTemplate
    private final SqlSessionTemplate sqlSessionTemplate;
    public JoinDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

//    判断电话号码和邮箱是否被使用
    @Override
    public boolean registrationCheck(RegisterEntityDao registerEntityDao) {
        JoinMapper mapper = sqlSessionTemplate.getMapper(JoinMapper.class);
        int emailCount = mapper.countEmailNumberOfJoin(registerEntityDao.getUserEmail());
        int phoneNumberCount = mapper.countPhoneNumberOfJoin(registerEntityDao.getUserPhoneNumber());
        return emailCount == 0 &&  phoneNumberCount == 0;
    }

//    注册添加
    @Override
    public boolean registrationInsert(RegisterEntityDao registerEntityDao) {
        JoinMapper mapper = sqlSessionTemplate.getMapper(JoinMapper.class);
        return mapper.insertOfRegister(registerEntityDao) != 0;
    }

//    登录查询
    @Override
    public LoginEntityDaoOutpt account(LoginEntityDaoInput loginEntityDaoInput) {
        return sqlSessionTemplate.getMapper(JoinMapper.class).account(loginEntityDaoInput);
    }

//    添加userKey
    @Override
    public boolean addUserKey(LoginEntityDaoInput loginEntityDaoInput, String userKey) {
        return sqlSessionTemplate.getMapper(JoinMapper.class).addUserKey(loginEntityDaoInput, userKey) != 0;
    }


}
