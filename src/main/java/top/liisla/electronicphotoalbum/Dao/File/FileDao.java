package top.liisla.electronicphotoalbum.Dao.File;

import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToImgIDEntityReturn;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToUserIDEntityReturn;

import java.util.ArrayList;
import java.util.HashMap;

public interface FileDao {
    boolean storageImgInfo(String URL, HashMap<String, String> cookieValueMap, UploadImgFileEntityController uploadImgFileEntityController);
    ArrayList<QueryImgToUserIDEntityReturn.ImgInfoListEntity> queryImgToUserIDOfMapper(String userID);
    QueryImgToImgIDEntityReturn getImgToImgID(String imgID);
}
