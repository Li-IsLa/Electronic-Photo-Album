package top.liisla.electronicphotoalbum.Dao.Join.Impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import top.liisla.electronicphotoalbum.Dao.Join.JoinDao;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.JoinEntityDao;
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
    public boolean registrationCheck(JoinEntityDao joinEntityDao) {
        JoinMapper mapper = sqlSessionTemplate.getMapper(JoinMapper.class);
        int emailCount = mapper.countEmailNumberOfJoin(joinEntityDao.getUserEmail());
        int phoneNumberCount = mapper.countPhoneNumberOfJoin(joinEntityDao.getUserPhoneNumber());
        return emailCount == 0 &&  phoneNumberCount == 0;
    }

//    注册添加
    @Override
    public boolean registrationInsert(JoinEntityDao joinEntityDao) {
        JoinMapper mapper = sqlSessionTemplate.getMapper(JoinMapper.class);
        return mapper.insertOfRegister(joinEntityDao) != 0;
    }
}
