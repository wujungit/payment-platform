package com.kanghe.payment.platform.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 15:16
 * @Description:
 */
public interface IOrderService {

    int createOrder(JSONObject payOrder);

}
