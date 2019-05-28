package com.kanghe.payment.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * t_iap_receipt
 *
 * @author
 */
@Getter
@Setter
public class IapReceipt implements Serializable {
    /**
     * 支付订单号
     */
    private String payOrderId;

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * IAP业务号
     */
    private String transactionId;

    /**
     * 处理状态:0-未处理,1-处理成功,-1-处理失败
     */
    private Byte status;

    /**
     * 处理次数
     */
    private Byte handleCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 渠道ID
     */
    private String receiptData;
}