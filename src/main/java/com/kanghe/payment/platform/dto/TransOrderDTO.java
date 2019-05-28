package com.kanghe.payment.platform.dto;

import com.kanghe.payment.platform.base.PageParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 20:48
 * @Description:
 **/
@Getter
@Setter
public class TransOrderDTO extends PageParam {

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 转账订单号
     */
    private String transOrderId;

    /**
     * 商户转账单号
     */
    private String mchTransNo;

    /**
     * 渠道订单号
     */
    private String channelOrderNo;

    /**
     * 转账状态:0-订单生成,1-转账中,2-转账成功,3-转账失败,4-业务处理完成
     */
    private Byte status;

}
