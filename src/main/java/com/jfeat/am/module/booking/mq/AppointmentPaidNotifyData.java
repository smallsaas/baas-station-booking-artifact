package com.jfeat.am.module.booking.mq;

import java.io.Serializable;

/**
 * Created by jackyhuang on 2017/8/16.
 */
public class AppointmentPaidNotifyData implements Serializable {
    private String id;
    private String transactionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "VipPaidNotifyData{" +
                "id='" + id + '\'' +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
