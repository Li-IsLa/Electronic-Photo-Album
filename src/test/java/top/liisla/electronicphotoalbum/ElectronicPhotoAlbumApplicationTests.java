package top.liisla.electronicphotoalbum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.liisla.electronicphotoalbum.Dao.File.FileDao;
import top.liisla.electronicphotoalbum.Entity.Return.file.QueryImgToImgIDEntityReturn;

@SpringBootTest
class ElectronicPhotoAlbumApplicationTests {


    private FileDao fileDao;

    @Autowired  // 使用setter注入
    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Test
    void contextLoads() {
        QueryImgToImgIDEntityReturn imgToImgID = fileDao.getImgToImgID("2");
        System.out.println(imgToImgID);
    }
}
