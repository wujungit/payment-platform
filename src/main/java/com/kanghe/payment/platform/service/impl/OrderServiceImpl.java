package com.kanghe.payment.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kanghe.payment.platform.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 15:16
 * @Description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Override
    public int createOrder(JSONObject payOrder) {
        return 0;
    }
}
