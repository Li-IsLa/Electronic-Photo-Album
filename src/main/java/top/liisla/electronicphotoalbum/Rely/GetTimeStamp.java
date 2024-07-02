package top.liisla.electronicphotoalbum.Rely;

import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class GetTimeStamp {
    public Long getTimeStampOfUTC8() {
        ZoneId zoneId = ZoneId.of("Asia/Shanghai"); // UTC+8
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        return zonedDateTime.toInstant().toEpochMilli();
    }
}
