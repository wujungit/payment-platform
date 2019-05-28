package com.kanghe.payment.platform.service;

import com.kanghe.payment.platform.entity.GoodsOrder;
import com.kanghe.payment.platform.mapper.GoodsOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: W_jun1
 * @Date: 2019/5/28 17:08
 * @Description:
 */
@Service
public class GoodsOrderService {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    public int addGoodsOrder(GoodsOrder goodsOrder) {
        return goodsOrderMapper.insertSelective(goodsOrder);
    }

    public int update(GoodsOrder goodsOrder) {
        return goodsOrderMapper.updateByPrimaryKeySelective(goodsOrder);
    }

}
