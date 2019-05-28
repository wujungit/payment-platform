package com.kanghe.payment.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * t_goods_order
 *
 * @author
 */
@Getter
@Setter
public class GoodsOrder implements Serializable {
    /**
     * 商品订单ID
     */
    private String goodsOrderId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 金额,单位分
     */
    private Long amount;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 订单状态,订单生成(0),支付成功(1),处理完成(2),处理失败(-1)
     */
    private Byte status;

    /**
     * 支付订单号
     */
    private String payOrderId;

    /**
     * 渠道ID
     */
    private String channelId;

    /**
     * 支付渠道用户ID(微信openID或支付宝账号等第三方支付账号)
     */
    private String channelUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}