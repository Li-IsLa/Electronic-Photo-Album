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
    private int Code;
    private ArrayList<ImgInfoListEntity> imgInfoList;


    public static class ImgInfoListEntity {
        private int imgID;
        private String imgName;
        private String imgTitle;
        private String imgURL;
        private String userID;
        private String imgTime;
    }
}
