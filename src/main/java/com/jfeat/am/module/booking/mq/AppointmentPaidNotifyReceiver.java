package com.jfeat.am.module.booking.mq;

import com.alibaba.fastjson.JSON;
import com.jfeat.am.module.booking.services.domain.definition.AppointmentStatus;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import com.jfeat.am.module.booking.services.service.crud.AppointmentService;
import com.jfinal.kit.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by jackyhuang on 2017/8/16.
 */
@Component
@RabbitListener(queues = Const.APPOINTMENT_PAID_NOTIFY_QUEUE)
public class AppointmentPaidNotifyReceiver {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentPaidNotifyReceiver.class);

    @Autowired
    private AppointmentService appointmentService;

    @RabbitHandler
    public void process(@Payload String message) {
        logger.info("Receiver : " + message);
        if (StrKit.isBlank(message)) {
            return;
        }
        AppointmentPaidNotifyData data = JSON.parseObject(message, AppointmentPaidNotifyData.class);
        Appointment appointment = appointmentService.retrieveMaster(Long.parseLong(data.getId()));
        if (appointment == null) {
            logger.error("appointment not found. id = {}", data.getId());
            return;
        }
        appointment.setStatus(AppointmentStatus.TO_BE_COMFIRMED.toString());
        Integer result = appointmentService.updateMaster(appointment);
        logger.debug("update appointment result = {}. appointment={}", result, appointment);
    }
}
