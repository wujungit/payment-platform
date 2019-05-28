package com.kanghe.payment.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * t_pay_channel
 *
 * @author
 */
@Getter
@Setter
public class PayChannel implements Serializable {
    /**
     * 渠道主键ID
     */
    private Integer id;

    /**
     * 渠道ID
     */
    private String channelId;

    /**
     * 渠道名称,如:alipay,wechat
     */
    private String channelName;

    /**
     * 渠道商户ID
     */
    private String channelMchId;

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 渠道状态,0-停止使用,1-使用中
     */
    private Byte state;

    /**
     * 配置参数,json字符串
     */
    private String param;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}