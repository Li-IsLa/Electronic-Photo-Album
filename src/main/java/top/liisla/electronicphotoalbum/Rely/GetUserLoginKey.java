package top.liisla.electronicphotoalbum.Rely;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class GetUserLoginKey {
    public String getUserKey(String input) {
        input += getCurrentTimestamp();
        try {
            // 获取一个MD5的MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 计算MD5函数
            md.update(input.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            // 转换为16进制并返回
            return String.format("%032x", new BigInteger(1, digest));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCurrentTimestamp() {
        // 获取当前的时间（UTC+8）
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));

        // 将时间转换为字符串
        return now.format(DateTimeFormatter.ISO_INSTANT);
    }
}