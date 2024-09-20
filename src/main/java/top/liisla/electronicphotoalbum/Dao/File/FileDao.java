package top.liisla.electronicphotoalbum.Dao.File;

import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;

import java.util.HashMap;

public interface FileDao {
    boolean storageImgInfo(String URL, HashMap<String, String> cookieValueMap, UploadImgFileEntityController uploadImgFileEntityController);
}
