package com.kanghe.payment.platform.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 13:37
 * @Description:
 */
public interface IAliPayService {

    String doAliPayWapReq(JSONObject payOrder, String resKey);

    String doAliPayPcReq(JSONObject payOrder, String resKey);

    String doAliPayMobileReq(JSONObject payOrder, String resKey);

    String doAliPayQrReq(JSONObject payOrder, String resKey);

    String handleAliPayNotify(Map params);

}
