package com.kanghe.payment.platform.controller.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanghe.payment.platform.constant.PayConstant;
import com.kanghe.payment.platform.entity.MchInfo;
import com.kanghe.payment.platform.entity.PayChannel;
import com.kanghe.payment.platform.service.*;
import com.kanghe.payment.platform.util.MySeq;
import com.kanghe.payment.platform.util.XXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MchInfoService mchInfoService;
    @Autowired
    private PayChannelService payChannelService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IWxPayService wxPayService;
    @Autowired
    private IAliPayService aliPayService;

    /**
     * 统一下单
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
        String logPrefix = "【商户统一下单】";
        log.info("{}createOrder...,params={}", logPrefix, params);
        JSONObject jo = JSONObject.parseObject(params);
        JSONObject payContext = new JSONObject();
        JSONObject payOrder = null;
        // 参数校验
        Object o = verifyParams(jo, payContext);
        if (o instanceof String) {
            return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, (String) o, null, null));
        }
        if (o instanceof JSONObject) {
            payOrder = (JSONObject) o;
        }
        if (payOrder == null) {
            return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心下单失败", null, null));
        }
        // 创建支付订单
        int result = orderService.createOrder(payOrder);
        if (result != 1) {
            return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "创建支付订单失败", null, null));
        }
        String channelId = payOrder.getString("channelId");
        switch (channelId) {
            case PayConstant.PAY_CHANNEL_WX_APP:
                return wxPayService.doWxPayReq(PayConstant.WxConstant.TRADE_TYPE_APP, payOrder, payContext.getString("resKey"));
            case PayConstant.PAY_CHANNEL_WX_JSAPI:
                return wxPayService.doWxPayReq(PayConstant.WxConstant.TRADE_TYPE_JSPAI, payOrder, payContext.getString("resKey"));
            case PayConstant.PAY_CHANNEL_WX_NATIVE:
                return wxPayService.doWxPayReq(PayConstant.WxConstant.TRADE_TYPE_NATIVE, payOrder, payContext.getString("resKey"));
            case PayConstant.PAY_CHANNEL_WX_MWEB:
                return wxPayService.doWxPayReq(PayConstant.WxConstant.TRADE_TYPE_MWEB, payOrder, payContext.getString("resKey"));
            case PayConstant.PAY_CHANNEL_ALIPAY_MOBILE:
                return aliPayService.doAliPayMobileReq(payOrder, payContext.getString("resKey"));
            case PayConstant.PAY_CHANNEL_ALIPAY_PC:
                return aliPayService.doAliPayPcReq(payOrder, payContext.getString("resKey"));
            case PayConstant.PAY_CHANNEL_ALIPAY_WAP:
                return aliPayService.doAliPayWapReq(payOrder, payContext.getString("resKey"));
            case PayConstant.PAY_CHANNEL_ALIPAY_QR:
                return aliPayService.doAliPayQrReq(payOrder, payContext.getString("resKey"));
            default:
                return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "不支持的支付渠道类型[channelId=" + channelId + "]", null, null));
        }
    }

    private Object verifyParams(JSONObject jo, JSONObject payContext) {
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
        // 参数校验
        String errorMessage = "";
        if (StringUtils.isBlank(mchId)) {
            errorMessage = "request params[mchId] error.";
            return errorMessage;
        }
        if (StringUtils.isBlank(mchOrderNo)) {
            errorMessage = "request params[mchOrderNo] error.";
            return errorMessage;
        }
        if (StringUtils.isBlank(channelId)) {
            errorMessage = "request params[channelId] error.";
            return errorMessage;
        }
        if (!StringUtils.isNumeric(amount)) {
            errorMessage = "request params[amount] error.";
            return errorMessage;
        }
        if (StringUtils.isBlank(currency)) {
            errorMessage = "request params[currency] error.";
            return errorMessage;
        }
        if (StringUtils.isBlank(notifyUrl)) {
            errorMessage = "request params[notifyUrl] error.";
            return errorMessage;
        }
        if (StringUtils.isBlank(subject)) {
            errorMessage = "request params[subject] error.";
            return errorMessage;
        }
        if (StringUtils.isBlank(body)) {
            errorMessage = "request params[body] error.";
            return errorMessage;
        }
        if (StringUtils.isBlank(sign)) {
            errorMessage = "request params[sign] error.";
            return errorMessage;
        }
        // 特定渠道发起时额外参数校验
        if (PayConstant.PAY_CHANNEL_WX_JSAPI.equalsIgnoreCase(channelId)) {
            if (StringUtils.isEmpty(extra)) {
                errorMessage = "request params[extra] error.";
                return errorMessage;
            }
            JSONObject extraObject = JSON.parseObject(extra);
            String openId = extraObject.getString("openId");
            if (StringUtils.isBlank(openId)) {
                errorMessage = "request params[extra.openId] error.";
                return errorMessage;
            }
        } else if (PayConstant.PAY_CHANNEL_WX_NATIVE.equalsIgnoreCase(channelId)) {
            if (StringUtils.isEmpty(extra)) {
                errorMessage = "request params[extra] error.";
                return errorMessage;
            }
            JSONObject extraObject = JSON.parseObject(extra);
            String productId = extraObject.getString("productId");
            if (StringUtils.isBlank(productId)) {
                errorMessage = "request params[extra.productId] error.";
                return errorMessage;
            }
        } else if (PayConstant.PAY_CHANNEL_WX_MWEB.equalsIgnoreCase(channelId)) {
            if (StringUtils.isEmpty(extra)) {
                errorMessage = "request params[extra] error.";
                return errorMessage;
            }
            JSONObject extraObject = JSON.parseObject(extra);
            String productId = extraObject.getString("sceneInfo");
            if (StringUtils.isBlank(productId)) {
                errorMessage = "request params[extra.sceneInfo] error.";
                return errorMessage;
            }
            if (StringUtils.isBlank(clientIp)) {
                errorMessage = "request params[clientIp] error.";
                return errorMessage;
            }
        }
        // 查询商户信息
        MchInfo mchInfo = mchInfoService.selectMchInfo(mchId);
        if (mchInfo == null) {
            errorMessage = "Can't found mchInfo[mchId=" + mchId + "] record in db.";
            return errorMessage;
        }
        if (mchInfo.getState() != 1) {
            errorMessage = "mchInfo not available [mchId=" + mchId + "] record in db.";
            return errorMessage;
        }
        if (StringUtils.isBlank(mchInfo.getReqKey())) {
            errorMessage = "reqKey is null[mchId=" + mchId + "] record in db.";
            return errorMessage;
        }
        payContext.put("resKey", mchInfo.getResKey());
        // 查询商户对应的支付渠道
        PayChannel payChannel = payChannelService.selectPayChannel(mchId, channelId);
        if (payChannel == null) {
            errorMessage = "Can't found payChannel[channelId=" + channelId + ",mchId=" + mchId + "] record in db.";
            return errorMessage;
        }
        if (payChannel.getState() != 1) {
            errorMessage = "channel not available [channelId=" + channelId + ",mchId=" + mchId + "]";
            return errorMessage;
        }
        // 验签
        if (!XXPayUtil.verifyPaySign(jo, mchInfo.getReqKey())) {
            errorMessage = "Verify XX pay sign failed.";
            return errorMessage;
        }
        // 验证参数通过，返回JSONObject对象
        JSONObject payOrder = new JSONObject();
        payOrder.put("payOrderId", MySeq.getPay());
        payOrder.put("mchId", mchId);
        payOrder.put("mchOrderNo", mchOrderNo);
        payOrder.put("channelId", channelId);
        payOrder.put("amount", Long.parseLong(amount));
        payOrder.put("currency", currency);
        payOrder.put("clientIp", clientIp);
        payOrder.put("device", device);
        payOrder.put("subject", subject);
        payOrder.put("body", body);
        payOrder.put("extra", extra);
        payOrder.put("channelMchId", payChannel.getChannelMchId());
        payOrder.put("param1", param1);
        payOrder.put("param2", param2);
        payOrder.put("notifyUrl", notifyUrl);
        payOrder.put("resKey", mchInfo.getResKey());
        return payOrder;
    }
}