package com.kanghe.payment.platform.controller.shop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanghe.payment.platform.constant.Constant;
import com.kanghe.payment.platform.entity.GoodsOrder;
import com.kanghe.payment.platform.service.GoodsOrderService;
import com.kanghe.payment.platform.util.AmountUtil;
import com.kanghe.payment.platform.util.DateUtil;
import com.kanghe.payment.platform.util.PayDigestUtil;
import com.kanghe.payment.platform.util.XXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 17:05
 * @Description:
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsOrderController {

    @Autowired
    private GoodsOrderService goodsOrderService;

    private static final String mchId = "20001223";
    // 加签key
    private static final String reqKey = "M86l522AV6q613Ii4W6u8K48uW8vM1N6bFgyv769220MdYe9u37N4y7rI5mQ";
    // 验签key
    private static final String resKey = "Hpcl522AV6q613KIi46u6g6XuW8vM1N8bFgyv769770MdYe9u37M4y7rIpl8";
    //static final String baseUrl = "http://api.xxpay.org/api";
    private static final String baseUrl = "http://127.0.0.1:3020/api";
    //static final String notifyUrl = "http://shop.xxpay.org/goods/payNotify";
    private static final String notifyUrl = "http://127.0.0.1:8081/goods/payNotify";
    private final static String QR_PAY_URL = "http://shop.xxpay.org/goods/qrPay.html";
    private static final String AppID = "wx077cb62e341f8a5c";
    private static final String AppSecret = "e663ea068f3e4f952f143de1432a35c2";
    private static final String GetOpenIdURL = "http://shop.xxpay.org/goods/getOpenId";
    private static final String GetOpenIdURL2 = "http://shop.xxpay.org/goods/getOpenId2";

    private AtomicLong seq = new AtomicLong(0L);

    @RequestMapping("/openQrPay.html")
    public String openQrPay(ModelMap model) {
        return "openQrPay";
    }

    @RequestMapping("/qrPay.html")
    public String qrPay(ModelMap model, HttpServletRequest request, Long amount) {
        String logPrefix = "【二维码扫码支付】";
        String view = "qrPay";
        log.info("====== 开始接收二维码扫码支付请求 ======");
        String ua = request.getHeader("User-Agent");
        String goodsId = "G_0001";
        log.info("{}接收参数:goodsId={},amount={},ua={}", logPrefix, goodsId, amount, ua);
        String client = "alipay";
        String channelId = "ALIPAY_WAP";
        if (StringUtils.isBlank(ua)) {
            String errorMessage = "User-Agent为空！";
            log.info("{}信息：{}", logPrefix, errorMessage);
            model.put("result", "failed");
            model.put("resMsg", errorMessage);
            return view;
        } else {
            if (ua.contains("Alipay")) {
                client = "alipay";
                channelId = "ALIPAY_WAP";
            } else if (ua.contains("MicroMessenger")) {
                client = "wx";
                channelId = "WX_JSAPI";
            }
        }
        if (client == null) {
            String errorMessage = "请用微信或支付宝扫码";
            log.info("{}信息：{}", logPrefix, errorMessage);
            model.put("result", "failed");
            model.put("resMsg", errorMessage);
            return view;
        }
        // 先插入订单数据
        GoodsOrder goodsOrder = null;
        Map<String, String> orderMap = null;
        if ("alipay".equals(client)) {
            log.info("{}{}扫码下单", logPrefix, "支付宝");
            Map params = new HashMap<>();
            params.put("channelId", channelId);
            // 下单
            goodsOrder = createGoodsOrder(goodsId, amount);
            orderMap = createPayOrder(goodsOrder, params);
        } else if ("wx".equals(client)) {
            log.info("{}{}扫码", logPrefix, "微信");
            // 判断是否拿到openid，如果没有则去获取
            String openId = request.getParameter("openId");
            if (StringUtils.isNotBlank(openId)) {
                log.info("{}openId：{}", logPrefix, openId);
                Map params = new HashMap<>();
                params.put("channelId", channelId);
                params.put("openId", openId);
                goodsOrder = createGoodsOrder(goodsId, amount);
                // 下单
                orderMap = createPayOrder(goodsOrder, params);
            } else {
                String redirectUrl = QR_PAY_URL + "?amount=" + amount;
                String url = GetOpenIdURL2 + "?redirectUrl=" + redirectUrl;
                log.info("跳转URL={}", url);
                return "redirect:" + url;
            }
        }
        model.put("goodsOrder", goodsOrder);
        model.put("amount", AmountUtil.convertCent2Dollar(goodsOrder.getAmount() + ""));
        if (orderMap != null) {
            model.put("orderMap", orderMap);
            String payOrderId = orderMap.get("payOrderId");
            GoodsOrder go = new GoodsOrder();
            go.setGoodsOrderId(goodsOrder.getGoodsOrderId());
            go.setPayOrderId(payOrderId);
            go.setChannelId(channelId);
            int ret = goodsOrderService.update(go);
            log.info("修改商品订单,返回:{}", ret);
        }
        model.put("client", client);
        return view;
    }

    GoodsOrder createGoodsOrder(String goodsId, Long amount) {
        // 先插入订单数据
        String goodsOrderId = String.format("%s%s%06d", "G", DateUtil.getSeqString(), (int) seq.getAndIncrement() % 1000000);
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setGoodsOrderId(goodsOrderId);
        goodsOrder.setGoodsId(goodsId);
        goodsOrder.setGoodsName("XXPAY捐助商品G_0001");
        goodsOrder.setAmount(amount);
        goodsOrder.setUserId("xxpay_000001");
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_INIT);
        int result = goodsOrderService.addGoodsOrder(goodsOrder);
        log.info("插入商品订单,返回:{}", result);
        return goodsOrder;
    }

    private Map createPayOrder(GoodsOrder goodsOrder, Map<String, Object> params) {
        JSONObject paramMap = new JSONObject();
        paramMap.put("mchId", mchId);                       // 商户ID
        paramMap.put("mchOrderNo", goodsOrder.getGoodsOrderId());           // 商户订单号
        paramMap.put("channelId", params.get("channelId"));             // 支付渠道ID, WX_NATIVE,ALIPAY_WAP
        paramMap.put("amount", goodsOrder.getAmount());                          // 支付金额,单位分
        paramMap.put("currency", "cny");                    // 币种, cny-人民币
        paramMap.put("clientIp", "114.112.124.236");        // 用户地址,IP或手机号
        paramMap.put("device", "WEB");                      // 设备
        paramMap.put("subject", goodsOrder.getGoodsName());
        paramMap.put("body", goodsOrder.getGoodsName());
        paramMap.put("notifyUrl", notifyUrl);         // 回调URL
        paramMap.put("param1", "");                         // 扩展参数1
        paramMap.put("param2", "");                         // 扩展参数2

        JSONObject extra = new JSONObject();
        extra.put("openId", params.get("openId"));
        paramMap.put("extra", extra.toJSONString());  // 附加参数

        String reqSign = PayDigestUtil.getSign(paramMap, reqKey);
        paramMap.put("sign", reqSign);   // 签名
        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求支付中心下单接口,请求数据:" + reqData);
        String url = baseUrl + "/pay/create_order?";
        String result = XXPayUtil.call4Post(url + reqData);
        System.out.println("请求支付中心下单接口,响应数据:" + result);
        Map retMap = JSON.parseObject(result);
        if ("SUCCESS".equals(retMap.get("retCode"))) {
            // 验签
            String checkSign = PayDigestUtil.getSign(retMap, resKey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if (checkSign.equals(retSign)) {
                System.out.println("=========支付中心下单验签成功=========");
            } else {
                System.err.println("=========支付中心下单验签失败=========");
                return null;
            }
        }
        return retMap;
    }

}
