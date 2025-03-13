package za.co.moson.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class CheckUtil {

    public boolean isEmpty(Object object) {
        return ObjectUtils.isEmpty(object);
    }

    public boolean isNotEmpty(Object object) {
        return !ObjectUtils.isEmpty(object);
    }
}
