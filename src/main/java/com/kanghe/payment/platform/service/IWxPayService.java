package com.kanghe.payment.platform.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 11:23
 * @Description:
 */
public interface IWxPayService {

    String doWxPayReq(String tradeType, JSONObject payOrder, String resKey);

    String handleWxPayNotify(String params);

}
