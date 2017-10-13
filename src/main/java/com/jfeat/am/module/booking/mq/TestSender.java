package com.jfeat.am.module.booking.mq;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Created by jackyhuang on 2017/8/16.
 */
@Component
public class TestSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        Map<String, Object> data = Maps.newHashMap();
        String context = "hello" + new Date();
        data.put("id", "111111");
        data.put("transactionId", context);
        System.out.println("Sender : " + data);
        this.rabbitTemplate.convertAndSend(Const.APPOINTMENT_PAID_NOTIFY_QUEUE, JSON.toJSONString(data));
    }
}
