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
    public String doAliPayReq(String channelId, JSONObject payOrder, String resKey) {
        return null;
    }

    @Override
    public String handleAliPayNotify(Map params) {
        return null;
    }
}
