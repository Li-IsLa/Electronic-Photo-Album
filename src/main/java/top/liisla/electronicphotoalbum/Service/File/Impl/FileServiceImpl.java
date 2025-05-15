package top.liisla.electronicphotoalbum.Service.File.Impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.liisla.electronicphotoalbum.Dao.File.FileDao;
import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.CodeEntityReturn;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToImgIDEntityReturn;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToUserIDEntityReturn;
import top.liisla.electronicphotoalbum.Rely.GetCookieValue;
import top.liisla.electronicphotoalbum.Rely.GetTimeStamp;
import top.liisla.electronicphotoalbum.Rely.SaveFile;
import top.liisla.electronicphotoalbum.Service.File.FileService;

import java.util.*;

@Service
public class FileServiceImpl implements FileService {


    private final SaveFile saveFile;
    private final GetTimeStamp getTimeStamp;
    private final GetCookieValue getCookieValue;
    private final FileDao fileDao;
    public FileServiceImpl(SaveFile saveFile, GetTimeStamp getTimeStamp, GetCookieValue getCookieValue, FileDao fileDao) {
        this.saveFile = saveFile;
        this.getTimeStamp = getTimeStamp;
        this.getCookieValue = getCookieValue;
        this.fileDao = fileDao;
    }

    @Override
    public CodeEntityReturn uploadFileImg(MultipartFile file, HttpServletRequest request,
                                          UploadImgFileEntityController uploadImgFileEntityController) {
        CodeEntityReturn codeEntityReturn = new CodeEntityReturn();
        Long timeStampOfUTC8 = getTimeStamp.getTimeStampOfUTC8();
        if (!saveFile.saveFileToLocal(file, timeStampOfUTC8.toString())) {
            codeEntityReturn.setCode(400);
            codeEntityReturn.setMessage("上传失败请稍后再试,这是服务器错误不是你的问题");
            return codeEntityReturn;
        }
//        获取cookie
        ArrayList<String> cookieName = new ArrayList<>() {{
            add("userID");
        }};
        HashMap<String, String> cookieValueMap = getCookieValue.getCookieValueByCookieName(request, cookieName);
//        文件保存成功,我们开始生成URL和存储数据库
//        获取URL
        String URL = "/upFileImg/" + timeStampOfUTC8 + saveFile.getFileSuffix(file);
//        存储数据库
        if (fileDao.storageImgInfo(URL, cookieValueMap, uploadImgFileEntityController)) {
            codeEntityReturn.setCode(200);
            codeEntityReturn.setMessage(URL);
        } else {
            codeEntityReturn.setCode(400);
            codeEntityReturn.setMessage("失败");
        }
        return codeEntityReturn;
    }

    @Override
    public QueryImgToUserIDEntityReturn queryImgToUserID(HttpServletRequest request) {
        List<String> getUserInfoInCookieArrayList = new ArrayList<>() {{
            add("userID");
            add("userName");
        }};
        HashMap<String, String> cookieValueMap = getCookieValue.getCookieValueByCookieName(request, getUserInfoInCookieArrayList);
        ArrayList<QueryImgToUserIDEntityReturn.ImgInfoListEntity> imgInfoListEntities = fileDao.queryImgToUserIDOfMapper(cookieValueMap.get("userID"));
        if (imgInfoListEntities == null) {
            return QueryImgToUserIDEntityReturn.builder()
                    .code(204)
                    .message("暂时没有图片")
                    .build();
        }
        return QueryImgToUserIDEntityReturn.builder()
                .userID(cookieValueMap.get("userID"))
                .userName(cookieValueMap.get("userName"))
                .queryTime(getTimeStamp.getTimeStampOfUTC8().toString())
                .imgInfoList(imgInfoListEntities)
                .code(200)
                .message("查询到以下图片")
                .build();
    }

    @Override
    public QueryImgToImgIDEntityReturn queryImgToImgID(String imgID) {
        QueryImgToImgIDEntityReturn imgInfoAndCommentInfo;
        try {
            imgInfoAndCommentInfo = fileDao.getImgToImgID(imgID);
        } catch (Exception e) {
            throw new RuntimeException("出现错误，请稍后再试。");
        }
        imgInfoAndCommentInfo.setCode(200);
        imgInfoAndCommentInfo.setMessage("成功");
        return imgInfoAndCommentInfo;
//        return null;
    }
}
