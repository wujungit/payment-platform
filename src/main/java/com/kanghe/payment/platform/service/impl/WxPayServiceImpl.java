package com.kanghe.payment.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kanghe.payment.platform.base.BaseParam;
import com.kanghe.payment.platform.constant.PayConstant;
import com.kanghe.payment.platform.service.IWxPayService;
import com.kanghe.payment.platform.util.JsonUtil;
import com.kanghe.payment.platform.util.RpcUtil;
import com.kanghe.payment.platform.util.XXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tradeType", tradeType);
        paramMap.put("payOrder", payOrder);
        String jsonParam = RpcUtil.createBaseParam(paramMap);
        Map<String, Object> result = doWxPayReq(jsonParam);
        String s = RpcUtil.mkRet(result);
        if (s == null) {
            return XXPayUtil.makeRetData(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_FAIL, "0111", "调用微信支付失败"), resKey);
        }
        Map<String, Object> map = XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_SUCCESS, null);
        map.putAll((Map) result.get("bizResult"));
        return XXPayUtil.makeRetData(map, resKey);
    }

    private Map doWxPayReq(String jsonParam) {
        String logPrefix = "【微信支付统一下单】";
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        return null;
    }

    @Override
    public String handleWxPayNotify(String params) {
        return null;
    }
}
