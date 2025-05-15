package top.liisla.electronicphotoalbum.Service.File;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.CodeEntityReturn;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToImgIDEntityReturn;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToUserIDEntityReturn;

public interface FileService {
    CodeEntityReturn uploadFileImg(MultipartFile file, HttpServletRequest request, UploadImgFileEntityController uploadImgFileEntityController);
    QueryImgToUserIDEntityReturn queryImgToUserID(HttpServletRequest request);
    QueryImgToImgIDEntityReturn queryImgToImgID(String imgID);
}
