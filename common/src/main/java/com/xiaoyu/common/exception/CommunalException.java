package com.xiaoyu.common.exception;

import com.xiaoyu.common.enums.ResultEnums;
import lombok.Data;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 10:13
 * @Modified By:
 */
@Data
public class CommunalException extends RuntimeException{

    private Integer code;

    public CommunalException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.code=resultEnums.getCode();
    }

    public CommunalException(Integer code,String msg){
        super(msg);
        this.code=code;
    }

}
