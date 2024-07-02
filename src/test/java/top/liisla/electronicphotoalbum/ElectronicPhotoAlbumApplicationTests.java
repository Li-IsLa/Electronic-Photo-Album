package top.liisla.electronicphotoalbum;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.liisla.electronicphotoalbum.Rely.GetTimeStamp;

@SpringBootTest
class ElectronicPhotoAlbumApplicationTests {

    @Test
    void contextLoads() {
        GetTimeStamp getTimeStamp = new GetTimeStamp();
        System.out.println(getTimeStamp.getTimeStampOfUTC8());
    }

}
