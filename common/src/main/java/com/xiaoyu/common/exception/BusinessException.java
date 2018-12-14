package com.xiaoyu.common.exception;

import com.xiaoyu.common.enums.ResultEnums;

public class BusinessException extends RuntimeException{

    private Integer code;

    public BusinessException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.code=resultEnums.getCode();
    }

    public BusinessException(Integer code, String msg){
        super(msg);
        this.code=code;
    }
}
