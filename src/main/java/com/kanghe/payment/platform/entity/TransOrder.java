package com.kanghe.payment.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * t_trans_order
 *
 * @author
 */
@Getter
@Setter
public class TransOrder implements Serializable {
    /**
     * 转账订单号
     */
    private String transOrderId;

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 商户转账单号
     */
    private String mchTransNo;

    /**
     * 渠道ID
     */
    private String channelId;

    /**
     * 转账金额,单位分
     */
    private Long amount;

    /**
     * 三位货币代码,人民币:cny
     */
    private String currency;

    /**
     * 转账状态:0-订单生成,1-转账中,2-转账成功,3-转账失败,4-业务处理完成
     */
    private Byte status;

    /**
     * 转账结果:0-不确认结果,1-等待手动处理,2-确认成功,3-确认失败
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
     * 订单转账成功时间
     */
    private Date transSuccTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}