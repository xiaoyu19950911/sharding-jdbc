package com.xiaoyu.common.exception;

import com.xiaoyu.common.enums.ResultEnums;

public class DaoException extends RuntimeException{

    private Integer code;

    public DaoException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.code=resultEnums.getCode();
    }

    public DaoException(Integer code,String msg){
        super(msg);
        this.code=code;
    }
}
