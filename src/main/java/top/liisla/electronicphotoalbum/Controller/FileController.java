package top.liisla.electronicphotoalbum.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.liisla.electronicphotoalbum.Entity.Contorller.File.UploadImgFileEntityController;
import top.liisla.electronicphotoalbum.Entity.Return.CodeEntityReturn;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToImgIDEntityReturn;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToUserIDEntityReturn;
import top.liisla.electronicphotoalbum.Service.File.FileService;

@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/api/uploadImg")
    public CodeEntityReturn uploadImgFile(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                          String imgName, String imgTitle, String imgContent) {
        UploadImgFileEntityController uploadImgFileEntityController = new UploadImgFileEntityController();
        uploadImgFileEntityController.setImgName(imgName);
        uploadImgFileEntityController.setImgTitle(imgTitle);
        uploadImgFileEntityController.setImgContent(imgContent);
        return fileService.uploadFileImg(file, request, uploadImgFileEntityController);
    }

//    查询所有图片根据userID来查询
    @GetMapping("/api/queryImgToUserID")
    public QueryImgToUserIDEntityReturn queryAllImgToUserID(HttpServletRequest request) {
        return fileService.queryImgToUserID(request);
    }

//    查询图片更具imgID来查询
    /*
    * <p>查询单张图片需要展示 <br>
    * 标题 发布人信息 发布时间 图片URL 点赞数量 图片名称 图片标题 图片简介<br>
    * 评论 评论人ID->评论人名称 评论内容 评论点赞数量 评论不喜欢数量 评论时间<br>
    * </p>
    * @param [int] imgID 用于查询图片
    * @Return [QueryImgToImgIDEntityReturn] 这个对象包含两部分其中QueryImgToImgIDEntityReturn对象实现的javaBean是图片的信息，<br>
    *                                       QueryImgToImgIDEntityReturn对象中的ImgCommentList对象是评论的集合，<br>
    *                                       这个集合最后会映射到QueryImgToImgIDEntityReturn对象中的imgCommentList
    * */
    @GetMapping("/api/queryImgToImgID")
    public QueryImgToImgIDEntityReturn queryImgToImgID(String imgID) {
        return fileService.queryImgToImgID(imgID);
    }
}
