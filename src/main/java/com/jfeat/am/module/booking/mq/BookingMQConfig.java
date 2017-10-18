package com.jfeat.am.module.booking.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jackyhuang on 2017/8/16.
 */
@Configuration
public class BookingMQConfig {

    @Bean
    public Queue appointmentPaidNotifyQueue() {
        return new Queue(Const.APPOINTMENT_PAID_NOTIFY_QUEUE);
    }

}
