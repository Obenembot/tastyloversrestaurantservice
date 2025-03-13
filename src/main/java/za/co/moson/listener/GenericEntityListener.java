//package za.co.moson.listener;
//
//import jakarta.persistence.PostPersist;
//import jakarta.persistence.PostUpdate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import za.co.hellogroup.data.velocitylimit.model.AuditTrail;
//import za.co.hellogroup.service.AuditTrailService;
//import za.co.hellogroup.utils.BuildUtil;
//import za.co.hellogroup.utils.Constants;
//
//import java.lang.reflect.Field;
//
//@Component
//public class GenericEntityListener<T> {
//    private static final Logger logger = LoggerFactory.getLogger(GenericEntityListener.class);
//    private static BuildUtil buildUtil;
//    private static AuditTrailService auditTrailService;
//
//
//    @Autowired
//    public void setGenericEntityListener(BuildUtil buildUtil, AuditTrailService auditTrailService) {
//        GenericEntityListener.buildUtil = buildUtil;
//        GenericEntityListener.auditTrailService = auditTrailService;
//    }
//
//    @PostPersist
//    public void postPersist(T entity) {
//        logger.info("[{}] [{}] [postPersist()] creating new audit trails {}", Constants.SERVICE_NAME, Constants.INFO, entity);
//        Class<?> aClass = entity.getClass();
//        AuditTrail auditTrail = buildAuditTrial(entity, aClass, "New");
//        auditTrailService.saveAuditTrail(buildUtil.buildCreate(auditTrail));
//    }
//
//    @PostUpdate
//    public void postUpdate(T entity) {
//        logger.info("[{}] [{}] [postUpdate()] updating audit trails {}", Constants.SERVICE_NAME, Constants.INFO, entity);
//        Class<?> aClass = entity.getClass();
//        AuditTrail auditTrail = buildAuditTrial(entity, aClass, "Change");
//        auditTrailService.saveAuditTrail(buildUtil.buildCreate(auditTrail));
//        logger.info("[{}] [{}] [postUpdate()] updating audit trails {} completed", Constants.SERVICE_NAME, Constants.INFO, entity);
//
//    }
//
//    AuditTrail buildAuditTrial(T entity, Class<?> aClass, String typeOfLog) {
//        try {
//            AuditTrail auditTrial = new AuditTrail();
//            Field customerIdField = aClass.getDeclaredField("id");
//            customerIdField.setAccessible(true);
//            Long customerId = (Long) customerIdField.get(entity);
//            auditTrial.setEntityId(customerId);
//            auditTrial.setEntityType(aClass.getName());
//            auditTrial.setPayload(entity.toString());
//            auditTrial.setTypeOfLog(typeOfLog);
//
//            return auditTrial;
//        } catch (Exception e) {
//            logger.error("[{}] [{}] [buildAuditTrial()] error occurred while converting audit trails {}", Constants.SERVICE_NAME, Constants.ERROR, e.getMessage());
//            return null;
//        }
//    }
//}