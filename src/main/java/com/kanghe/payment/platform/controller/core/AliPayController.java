package com.kanghe.payment.platform.controller.core;

import com.kanghe.payment.platform.service.IAliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 09:34
 * @Description:
 */
@Controller
@Slf4j
public class AliPayController {

    private IAliPayService aliPayService;

    /**
     * 支付宝支付回调
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/notify/pay/aliPayNotifyRes.htm")
    @ResponseBody
    public String aliPayNotifyRes(HttpServletRequest request) {
        log.info("接收支付宝支付回调通知...");
        String notifyRes = doAliPayRes(request);
        log.info("响应支付宝信息：{}", notifyRes);
        return notifyRes;
    }

    private String doAliPayRes(HttpServletRequest request) {
        return null;
    }
}
