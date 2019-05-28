package com.kanghe.payment.platform.service;

import com.kanghe.payment.platform.dto.PayOrderDTO;
import com.kanghe.payment.platform.entity.PayOrder;
import com.kanghe.payment.platform.mapper.PayOrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: PayOrderService
 */
@Service
public class PayOrderService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    public PayOrder selectPayOrder(String payOrderId) {
        return payOrderMapper.selectByPrimaryKey(payOrderId);
    }

    public List<PayOrder> getList(int offset, int limit, PayOrder payOrder) {
        PayOrderDTO dto = new PayOrderDTO();
        dto.setOrderByClause("create_time desc");
        dto.setOffset(offset);
        dto.setLimit(limit);
        setDTO(dto, payOrder);
        return payOrderMapper.selectList(dto);
    }

    public Integer getCount(PayOrder payOrder) {
        PayOrderDTO dto = new PayOrderDTO();
        setDTO(dto, payOrder);
        return payOrderMapper.selectCount(dto);
    }

    private void setDTO(PayOrderDTO dto, PayOrder payOrder) {
        if (payOrder != null) {
            if (StringUtils.isNotBlank(payOrder.getMchId())) {
                dto.setMchId(payOrder.getMchId());
            }
            if (StringUtils.isNotBlank(payOrder.getPayOrderId())) {
                dto.setPayOrderId(payOrder.getPayOrderId());
            }
            if (StringUtils.isNotBlank(payOrder.getMchOrderNo())) {
                dto.setMchOrderNo(payOrder.getMchOrderNo());
            }
            if (StringUtils.isNotBlank(payOrder.getChannelOrderNo())) {
                dto.setChannelOrderNo(payOrder.getChannelOrderNo());
            }
            if (payOrder.getStatus() != null && payOrder.getStatus() != -99) {
                dto.setStatus(payOrder.getStatus());
            }
        }
    }
}
