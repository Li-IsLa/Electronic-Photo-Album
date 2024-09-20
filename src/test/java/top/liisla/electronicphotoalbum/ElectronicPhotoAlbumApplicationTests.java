package top.liisla.electronicphotoalbum;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.liisla.electronicphotoalbum.Rely.GetTimeStamp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootTest
class ElectronicPhotoAlbumApplicationTests {

    @Test
    void contextLoads() {
        GetTimeStamp getTimeStamp = new GetTimeStamp();
        System.out.println(getTimeStamp.getTimeStampOfUTC8());
    }


}
