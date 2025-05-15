package top.liisla.electronicphotoalbum.Entity.Return.file;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;


@Data
@Builder
public class QueryImgToUserIDEntityReturn {
    private String userName;
    private String userID;
    private String queryTime;
    private int code;
    private String message;
    private ArrayList<ImgInfoListEntity> imgInfoList;

    @Data
    public static class ImgInfoListEntity {
        private int imgID;
        private String imgName;
        private String imgTitle;
        private String imgURL;
        private String userID;
        private String imgTime;
        private String imgUserLike;
    }
}
