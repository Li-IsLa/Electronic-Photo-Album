package top.liisla.electronicphotoalbum.Dao.Aspect.Impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import top.liisla.electronicphotoalbum.Dao.Aspect.AspectDao;
import top.liisla.electronicphotoalbum.Entity.Aspect.CheckUserLoginEntityAspect;
import top.liisla.electronicphotoalbum.Mapper.AspectMapper;

@Repository
public class AspectDaoImpl implements AspectDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public AspectDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    @Override
    public boolean checkUserLoginKey(CheckUserLoginEntityAspect checkUserLoginEntityAspect) {
        return sqlSessionTemplate.getMapper(AspectMapper.class).checkUserLoginKey(checkUserLoginEntityAspect);
    }
}
