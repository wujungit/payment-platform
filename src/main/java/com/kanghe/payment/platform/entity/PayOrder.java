package com.kanghe.payment.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * t_pay_order
 *
 * @author
 */
@Getter
@Setter
public class PayOrder implements Serializable {
    /**
     * 支付订单号
     */
    private String payOrderId;

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 商户订单号
     */
    private String mchOrderNo;

    /**
     * 渠道ID
     */
    private String channelId;

    /**
     * 支付金额,单位分
     */
    private Long amount;

    /**
     * 三位货币代码,人民币:cny
     */
    private String currency;

    /**
     * 支付状态,0-订单生成,1-支付中(目前未使用),2-支付成功,3-业务处理完成
     */
    private Byte status;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 设备
     */
    private String device;

    /**
     * 商品标题
     */
    private String subject;

    /**
     * 商品描述信息
     */
    private String body;

    /**
     * 特定渠道发起时额外参数
     */
    private String extra;

    /**
     * 渠道商户ID
     */
    private String channelMchId;

    /**
     * 渠道订单号
     */
    private String channelOrderNo;

    /**
     * 渠道支付错误码
     */
    private String errCode;

    /**
     * 渠道支付错误描述
     */
    private String errMsg;

    /**
     * 扩展参数1
     */
    private String param1;

    /**
     * 扩展参数2
     */
    private String param2;

    /**
     * 通知地址
     */
    private String notifyUrl;

    /**
     * 通知次数
     */
    private Byte notifyCount;

    /**
     * 最后一次通知时间
     */
    private Long lastNotifyTime;

    /**
     * 订单失效时间
     */
    private Long expireTime;

    /**
     * 订单支付成功时间
     */
    private Long paySuccTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}