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
public class RefundOrderDTO extends PageParam {

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 退款订单号
     */
    private String refundOrderId;

    /**
     * 商户退款单号
     */
    private String mchRefundNo;

    /**
     * 渠道订单号
     */
    private String channelOrderNo;

    /**
     * 退款状态:0-订单生成,1-退款中,2-退款成功,3-退款失败,4-业务处理完成
     */
    private Byte status;

}
