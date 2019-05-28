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
public class MchNotifyDTO extends PageParam {

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 订单类型:1-支付,2-转账,3-退款
     */
    private String orderType;

    /**
     * 商户订单号
     */
    private String mchOrderNo;

    /**
     * 通知状态,1-通知中,2-通知成功,3-通知失败
     */
    private Byte status;

}
