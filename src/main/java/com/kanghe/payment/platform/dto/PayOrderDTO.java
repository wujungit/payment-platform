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
public class PayOrderDTO extends PageParam {

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 支付订单号
     */
    private String payOrderId;

    /**
     * 商户订单号
     */
    private String mchOrderNo;

    /**
     * 渠道订单号
     */
    private String channelOrderNo;

    /**
     * 支付状态,0-订单生成,1-支付中(目前未使用),2-支付成功,3-业务处理完成
     */
    private Byte status;

}
