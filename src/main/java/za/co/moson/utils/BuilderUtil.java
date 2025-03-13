package za.co.moson.utils;

import org.springframework.stereotype.Component;
import za.co.moson.models.MultiEntity;

import java.time.LocalDateTime;

@Component
public class BuilderUtil {

    private final DateUtil dateTimeUtil;

    public BuilderUtil(DateUtil dateUtil) {
        this.dateTimeUtil = dateUtil;
    }


    public <T extends MultiEntity> T buildCreate(T entity, String zoneId) {
        LocalDateTime localDateTime = dateTimeUtil.currentDate(zoneId);
        entity.setCreatedAt(localDateTime);
        entity.setUpdatedAt(localDateTime);
        entity.setDeletedAt(null);
        return entity;
    }

    public <T extends MultiEntity> T buildUpdate(T entity, String zoneId) {
        LocalDateTime localDateTime = dateTimeUtil.currentDate(zoneId);
        entity.setUpdatedAt(localDateTime);
        entity.setDeletedAt(null);
        return entity;
    }


    public <T extends MultiEntity> T buildDelete(T entity, String zoneId) {
        LocalDateTime localDateTime = dateTimeUtil.currentDate(zoneId);
        entity.setUpdatedAt(localDateTime);
        entity.setDeletedAt(localDateTime);
        return entity;
    }
}
