package top.liisla.electronicphotoalbum.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;

import java.util.HashMap;

@Mapper
public interface FileMapper {

//    存储图片信息
    @Insert("insert into imginfo (imgName, imgTitle, imgContent, imgURL, userID) value (#{uploadImgFileEntityController.imgName}, #{uploadImgFileEntityController.imgTitle}, #{uploadImgFileEntityController.imgContent}, #{URL}, #{cookieValueMap.userID})")
    int storageImgInfo(String URL, HashMap<String, String> cookieValueMap, UploadImgFileEntityController uploadImgFileEntityController);
}
