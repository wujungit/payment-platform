package com.kanghe.payment.platform.dto;

import com.kanghe.payment.platform.base.PageParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 20:49
 * @Description:
 **/
@Getter
@Setter
public class MchInfoDTO extends PageParam {

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 类型
     */
    private String type;

}
