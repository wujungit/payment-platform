package com.kanghe.payment.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kanghe.payment.platform.service.IAliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 13:37
 * @Description:
 */
@Service
@Slf4j
public class AliPayServiceImpl implements IAliPayService {
    @Override
    public String doAliPayWapReq(JSONObject payOrder, String resKey) {
        String logPrefix = "【支付宝WAP支付下单】";
        return null;
    }

    @Override
    public String doAliPayPcReq(JSONObject payOrder, String resKey) {
        String logPrefix = "【支付宝PC支付下单】";
        return null;
    }

    @Override
    public String doAliPayMobileReq(JSONObject payOrder, String resKey) {
        String logPrefix = "【支付宝APP支付下单】";
        return null;
    }

    @Override
    public String doAliPayQrReq(JSONObject payOrder, String resKey) {
        String logPrefix = "【支付宝当面付之扫码支付下单】";
        return null;
    }

    @Override
    public String handleAliPayNotify(Map params) {
        return null;
    }
}
