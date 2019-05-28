package com.kanghe.payment.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * t_mch_notify
 *
 * @author
 */
@Getter
@Setter
public class MchNotify implements Serializable {
    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 商户订单号
     */
    private String mchOrderNo;

    /**
     * 订单类型:1-支付,2-转账,3-退款
     */
    private String orderType;

    /**
     * 通知地址
     */
    private String notifyUrl;

    /**
     * 通知次数
     */
    private Byte notifyCount;

    /**
     * 通知响应结果
     */
    private String result;

    /**
     * 通知状态,1-通知中,2-通知成功,3-通知失败
     */
    private Byte status;

    /**
     * 最后一次通知时间
     */
    private Date lastNotifyTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}