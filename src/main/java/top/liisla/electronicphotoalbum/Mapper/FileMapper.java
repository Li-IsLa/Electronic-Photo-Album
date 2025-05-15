package top.liisla.electronicphotoalbum.Mapper;

import org.apache.ibatis.annotations.*;
import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToImgIDEntityReturn;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToUserIDEntityReturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface FileMapper {

    //    存储图片信息
    @Insert("insert into imginfo (imgName, imgTitle, imgContent, imgURL, userID) value (#{uploadImgFileEntityController.imgName}, #{uploadImgFileEntityController.imgTitle}, #{uploadImgFileEntityController.imgContent}, #{URL}, #{cookieValueMap.userID})")
    int storageImgInfo(String URL, HashMap<String, String> cookieValueMap, UploadImgFileEntityController uploadImgFileEntityController);

    //    根据userID查询图片
    @Select("SELECT imgID, imgName, imgTitle, imgURL, userID, imgTime, imgUserLike  from imginfo where (userID = #{userID})")
    ArrayList<QueryImgToUserIDEntityReturn.ImgInfoListEntity> queryImgToUserIDOfMapper(String userID);

    // 1. 主查询必须返回imgID
    @Select("SELECT imgID, imgName, imgTitle, imgContent, imgURL, imgTime, imgUserLike, userID " +
            "FROM imginfo WHERE imgID = #{imgID}")
    @Results({
            @Result(property = "userName", column = "userID",
                    one = @One(select = "getUserNameByID")),
            @Result(property = "imgCommentListOfImg", column = "imgID",  // 确保column存在
                    many = @Many(select = "getCommentsByImgID"))
    })
    QueryImgToImgIDEntityReturn getImgDetail(@Param("imgID") int imgID);

    // 2. 确保评论查询使用正确的关联字段
    @Select("SELECT commentID, userID, commentUserContent, commentUserLike, " +
            "commentUserDisliking, commentTime FROM usercomment " +
            "WHERE imgID = #{imgID}")  // 确保这里是imgID（不是commentOfImgID）
    @Results({
            @Result(property = "userName", column = "userID",
                    one = @One(select = "getUserNameByID"))
    })
    List<QueryImgToImgIDEntityReturn.ImgCommentList> getCommentsByImgID(@Param("imgID") int imgID);

    // 查询用户名
    @Select("SELECT userName FROM userinfo WHERE userID = #{userID}")
    String getUserNameByID(@Param("userID") int userID);
}
