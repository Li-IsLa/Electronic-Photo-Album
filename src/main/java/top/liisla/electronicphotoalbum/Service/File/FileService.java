package top.liisla.electronicphotoalbum.Service.File;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.CodeEntityReturn;

public interface FileService {
    CodeEntityReturn uploadFileImg(MultipartFile file, HttpServletRequest request, UploadImgFileEntityController uploadImgFileEntityController);
}
