package com.jfeat.am.module.booking.services.domain.definition;

/**
 * Created by Administrator on 2017/10/14.
 */
public enum ServiceCode {
    NOT_ALLOW_TO_DELETE(2000);

    private int code;

     ServiceCode(int code){
        this.code=code;

    }
    public int getCode(){
        return this.code;
    }

    }
