package com.kanghe.payment.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kanghe.payment.platform.service.IWxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 11:23
 * @Description:
 */
@Service
@Slf4j
public class WxPayServiceImpl implements IWxPayService {
    @Override
    public String doWxPayReq(String tradeType, JSONObject payOrder, String resKey) {
        return null;
    }

    @Override
    public String handleWxPayNotify(String params) {
        return null;
    }
}
