package top.liisla.electronicphotoalbum.Dao.File.Impl;

import org.apache.ibatis.session.SqlSessionException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import top.liisla.electronicphotoalbum.Dao.File.FileDao;
import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToImgIDEntityReturn;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToUserIDEntityReturn;
import top.liisla.electronicphotoalbum.Mapper.FileMapper;

import java.util.ArrayList;
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

    @Override
    public ArrayList<QueryImgToUserIDEntityReturn.ImgInfoListEntity> queryImgToUserIDOfMapper(String userID) {
        ArrayList<QueryImgToUserIDEntityReturn.ImgInfoListEntity> imgInfoListEntities = sqlSessionTemplate.getMapper(FileMapper.class).queryImgToUserIDOfMapper(userID);
        if (imgInfoListEntities.isEmpty()) return null;
        return imgInfoListEntities;
    }

    @Override
    public QueryImgToImgIDEntityReturn getImgToImgID(String imgID) {
        try {
            int imgIDTypeInt = Integer.parseInt(imgID);
            return sqlSessionTemplate.getMapper(FileMapper.class).getImgDetail(imgIDTypeInt);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("imgID is not int");
        } catch(SqlSessionException e) {
            throw new SqlSessionException(e);
        }
    }
}
