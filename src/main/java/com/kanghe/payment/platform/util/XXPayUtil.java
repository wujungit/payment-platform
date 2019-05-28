package com.kanghe.payment.platform.util;

import com.alibaba.fastjson.JSON;
import com.kanghe.payment.platform.constant.PayConstant;
import com.kanghe.payment.platform.enums.PayEnum;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: 支付工具类
 */
@Slf4j
public class XXPayUtil {

    public static Map<String, Object> makeRetMap(String retCode, String retMsg, String resCode, String errCode, String errCodeDesc) {
        Map<String, Object> retMap = new HashMap<>();
        if (retCode != null) {
            retMap.put(PayConstant.RETURN_PARAM_RETCODE, retCode);
        }
        if (retMsg != null) {
            retMap.put(PayConstant.RETURN_PARAM_RETMSG, retMsg);
        }
        if (resCode != null) {
            retMap.put(PayConstant.RESULT_PARAM_RESCODE, resCode);
        }
        if (errCode != null) {
            retMap.put(PayConstant.RESULT_PARAM_ERRCODE, errCode);
        }
        if (errCodeDesc != null) {
            retMap.put(PayConstant.RESULT_PARAM_ERRCODEDES, errCodeDesc);
        }
        return retMap;
    }

    public static Map<String, Object> makeRetMap(String retCode, String retMsg, String resCode, PayEnum payEnum) {
        Map<String, Object> retMap = new HashMap<>();
        if (retCode != null) {
            retMap.put(PayConstant.RETURN_PARAM_RETCODE, retCode);
        }
        if (retMsg != null) {
            retMap.put(PayConstant.RETURN_PARAM_RETMSG, retMsg);
        }
        if (resCode != null) {
            retMap.put(PayConstant.RESULT_PARAM_RESCODE, resCode);
        }
        if (payEnum != null) {
            retMap.put(PayConstant.RESULT_PARAM_ERRCODE, payEnum.getCode());
            retMap.put(PayConstant.RESULT_PARAM_ERRCODEDES, payEnum.getMessage());
        }
        return retMap;
    }

    public static String makeRetData(Map retMap, String resKey) {
        if (retMap.get(PayConstant.RETURN_PARAM_RETCODE).equals(PayConstant.RETURN_VALUE_SUCCESS)) {
            String sign = PayDigestUtil.getSign(retMap, resKey, "payParams");
            retMap.put(PayConstant.RESULT_PARAM_SIGN, sign);
        }
        log.info("生成响应数据:{}", retMap);
        return JSON.toJSONString(retMap);
    }

    public static String makeRetFail(Map retMap) {
        log.info("生成响应数据:{}", retMap);
        return JSON.toJSONString(retMap);
    }

    /**
     * 验证支付中心签名
     *
     * @param params
     * @return
     */
    public static boolean verifyPaySign(Map<String, Object> params, String key) {
        String sign = (String) params.get("sign"); // 签名
        params.remove("sign");    // 不参与签名
        String checkSign = PayDigestUtil.getSign(params, key);
        if (!checkSign.equalsIgnoreCase(sign)) {
            return false;
        }
        return true;
    }

    /**
     * 验证VV平台支付中心签名
     *
     * @param params
     * @return
     */
    public static boolean verifyPaySign(Map<String, Object> params, String key, String... noSigns) {
        String sign = (String) params.get("sign"); // 签名
        params.remove("sign");    // 不参与签名
        if (noSigns != null && noSigns.length > 0) {
            for (String noSign : noSigns) {
                params.remove(noSign);
            }
        }
        String checkSign = PayDigestUtil.getSign(params, key);
        if (!checkSign.equalsIgnoreCase(sign)) {
            return false;
        }
        return true;
    }

    public static String genUrlParams(Map<String, Object> paraMap) {
        if (paraMap == null || paraMap.isEmpty()) {
            return "";
        }
        StringBuffer urlParam = new StringBuffer();
        Set<String> keySet = paraMap.keySet();
        int i = 0;
        for (String key : keySet) {
            urlParam.append(key).append("=").append(paraMap.get(key));
            if (++i == keySet.size()) {
                break;
            }
            urlParam.append("&");
        }
        return urlParam.toString();
    }

    /**
     * 发起HTTP/HTTPS请求(method=POST)
     *
     * @param url
     * @return
     */
    public static String call4Post(String url) {
        try {
            URL url1 = new URL(url);
            if ("https".equals(url1.getProtocol())) {
                return HttpClient.callHttpsPost(url);
            } else if ("http".equals(url1.getProtocol())) {
                return HttpClient.callHttpPost(url);
            } else {
                return "";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
