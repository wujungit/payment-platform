package com.kanghe.payment.platform.controller.core;

import com.kanghe.payment.platform.service.IWxPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 09:34
 * @Description:
 */
@Controller
@Slf4j
public class WxPayController {

    @Autowired
    private IWxPayService wxPayService;

    /**
     * 微信支付回调
     * 1)接收微信服务器通知
     * 2)微信通知处理，验签、更新订单、商户通知
     * 3)MQ接收并发送商户通知
     *
     * @param request
     * @return
     */
    @RequestMapping("/notify/pay/wxPayNotifyRes.htm")
    @ResponseBody
    public String wxPayNotifyRes(HttpServletRequest request) {
        log.info("接收微信支付回调通知...");
        String notifyRes = doWxPayRes(request);
        log.info("响应微信信息：{}", notifyRes);
        return notifyRes;
    }

    private String doWxPayRes(HttpServletRequest request) {
        String params = null;
        try {
            params = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wxPayService.handleWxPayNotify(params);
    }

}
