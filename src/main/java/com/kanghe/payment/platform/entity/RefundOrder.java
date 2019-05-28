package com.kanghe.payment.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * t_refund_order
 *
 * @author
 */
@Getter
@Setter
public class RefundOrder implements Serializable {
    /**
     * 退款订单号
     */
    private String refundOrderId;

    /**
     * 支付订单号
     */
    private String payOrderId;

    /**
     * 渠道支付单号
     */
    private String channelPayOrderNo;

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 商户退款单号
     */
    private String mchRefundNo;

    /**
     * 渠道ID
     */
    private String channelId;

    /**
     * 支付金额,单位分
     */
    private Long payAmount;

    /**
     * 退款金额,单位分
     */
    private Long refundAmount;

    /**
     * 三位货币代码,人民币:cny
     */
    private String currency;

    /**
     * 退款状态:0-订单生成,1-退款中,2-退款成功,3-退款失败,4-业务处理完成
     */
    private Byte status;

    /**
     * 退款结果:0-不确认结果,1-等待手动处理,2-确认成功,3-确认失败
     */
    private Byte result;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 设备
     */
    private String device;

    /**
     * 备注
     */
    private String remarkInfo;

    /**
     * 渠道用户标识,如微信openId,支付宝账号
     */
    private String channelUser;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 渠道商户ID
     */
    private String channelMchId;

    /**
     * 渠道订单号
     */
    private String channelOrderNo;

    /**
     * 渠道错误码
     */
    private String channelErrCode;

    /**
     * 渠道错误描述
     */
    private String channelErrMsg;

    /**
     * 特定渠道发起时额外参数
     */
    private String extra;

    /**
     * 通知地址
     */
    private String notifyUrl;

    /**
     * 扩展参数1
     */
    private String param1;

    /**
     * 扩展参数2
     */
    private String param2;

    /**
     * 订单失效时间
     */
    private Date expireTime;

    /**
     * 订单退款成功时间
     */
    private Date refundSuccTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}