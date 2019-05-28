package com.kanghe.payment.platform.controller.shop;

import com.kanghe.payment.platform.service.GoodsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/openQrPay.html")
    public String openQrPay(ModelMap model) {
        return "openQrPay";
    }

}
