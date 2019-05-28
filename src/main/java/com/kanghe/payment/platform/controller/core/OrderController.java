package com.kanghe.payment.platform.controller.core;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 09:37
 * @Description:
 */
@RestController
@Slf4j
public class OrderController {

    /**
     * 统一下单接口
     * 1)先验证接口参数以及签名信息
     * 2)验证通过创建支付订单
     * 3)根据商户选择渠道,调用支付服务进行下单
     * 4)返回下单数据
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/api/pay/create_order", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createOrder(@RequestParam String params) {
        log.info("createOrder...");
        String logPrefix = "【商户统一下单】";
        JSONObject jo = JSONObject.parseObject(params);

        String errorMessage = "";

        // 支付参数
        String mchId = jo.getString("mchId");              // 商户ID
        String mchOrderNo = jo.getString("mchOrderNo");    // 商户订单号
        String channelId = jo.getString("channelId");      // 渠道ID
        String amount = jo.getString("amount");            // 支付金额（单位分）
        String currency = jo.getString("currency");        // 币种
        String clientIp = jo.getString("clientIp");        // 客户端IP
        String device = jo.getString("device");            // 设备
        String extra = jo.getString("extra");              // 特定渠道发起时额外参数
        String param1 = jo.getString("param1");            // 扩展参数1
        String param2 = jo.getString("param2");            // 扩展参数2
        String notifyUrl = jo.getString("notifyUrl");      // 支付结果回调URL
        String sign = jo.getString("sign");                // 签名
        String subject = jo.getString("subject");          // 商品主题
        String body = jo.getString("body");                // 商品描述信息

        if (StringUtils.isBlank(mchId)) {
            errorMessage = "request params[mchId] error.";
        }
        if (StringUtils.isBlank(mchOrderNo)) {
            errorMessage = "request params[mchOrderNo] error.";
        }
        if (StringUtils.isBlank(channelId)) {
            errorMessage = "request params[channelId] error.";
        }
        if (!StringUtils.isNumeric(amount)) {
            errorMessage = "request params[amount] error.";
        }
        if (StringUtils.isBlank(currency)) {
            errorMessage = "request params[currency] error.";
        }
        if (StringUtils.isBlank(notifyUrl)) {
            errorMessage = "request params[notifyUrl] error.";
        }
        if (StringUtils.isBlank(subject)) {
            errorMessage = "request params[subject] error.";
        }
        if (StringUtils.isBlank(body)) {
            errorMessage = "request params[body] error.";
        }
        return null;
    }

}
