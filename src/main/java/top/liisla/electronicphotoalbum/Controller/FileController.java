package top.liisla.electronicphotoalbum.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.CodeEntityReturn;
import top.liisla.electronicphotoalbum.Service.File.FileService;

@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/uploadImg")
    public CodeEntityReturn uploadImgFile(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                          String imgName, String imgTitle, String imgContent) {
        UploadImgFileEntityController uploadImgFileEntityController = new UploadImgFileEntityController();
        uploadImgFileEntityController.setImgName(imgName);
        uploadImgFileEntityController.setImgTitle(imgTitle);
        uploadImgFileEntityController.setImgContent(imgContent);
        return fileService.uploadFileImg(file, request, uploadImgFileEntityController);
    }
}
