package top.liisla.electronicphotoalbum.Entity.Return.file;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class QueryImgToImgIDEntityReturn {
    private String imgName;
    private String imgTitle;
    private String imgContent;
    private String imgURL;
    private Timestamp imgTIme;
    private int imgUserLike;
    private int userID;
    private String userName;
    private int code;
    private String message;
    private List<ImgCommentList> imgCommentListOfImg;

    @Data
    public static class ImgCommentList {
        private int commentID;
        private String userName;
        private String commentUserContent;
        private int commentUserLike;
        private int commentUserDisLiking;
        private Timestamp commentTime;
    }
}
