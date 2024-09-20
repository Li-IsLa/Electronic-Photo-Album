package top.liisla.electronicphotoalbum.Dao.File.Impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import top.liisla.electronicphotoalbum.Dao.File.FileDao;
import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;
import top.liisla.electronicphotoalbum.Mapper.FileMapper;

import java.util.HashMap;

@Repository
public class FileDaoImpl implements FileDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public FileDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public boolean storageImgInfo(String URL, HashMap<String, String> cookieValueMap, UploadImgFileEntityController uploadImgFileEntityController) {
        return sqlSessionTemplate.getMapper(FileMapper.class).storageImgInfo(URL, cookieValueMap, uploadImgFileEntityController) != 0;
    }
}
