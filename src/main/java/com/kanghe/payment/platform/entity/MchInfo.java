package com.kanghe.payment.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * t_mch_info
 *
 * @author
 */
@Getter
@Setter
public class MchInfo implements Serializable {
    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 请求私钥
     */
    private String reqKey;

    /**
     * 响应私钥
     */
    private String resKey;

    /**
     * 商户状态,0-停止使用,1-使用中
     */
    private Byte state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}