package za.co.moson.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class DateUtil {


    public LocalDateTime currentDate(String zoneId) {
        ZoneId zoneId1 = ZoneId.of(zoneId);
        return LocalDateTime.now(zoneId1);
    }
}
