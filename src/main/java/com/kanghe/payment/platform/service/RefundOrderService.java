package com.kanghe.payment.platform.service;

import com.kanghe.payment.platform.dto.RefundOrderDTO;
import com.kanghe.payment.platform.entity.RefundOrder;
import com.kanghe.payment.platform.mapper.RefundOrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: RefundOrderService
 */
@Service
public class RefundOrderService {

    @Autowired
    private RefundOrderMapper refundOrderMapper;

    public RefundOrder selectRefundOrder(String refundOrderId) {
        return refundOrderMapper.selectByPrimaryKey(refundOrderId);
    }

    public List<RefundOrder> getList(int offset, int limit, RefundOrder refundOrder) {
        RefundOrderDTO dto = new RefundOrderDTO();
        dto.setOrderByClause("create_time desc");
        dto.setOffset(offset);
        dto.setLimit(limit);
        setDTO(dto, refundOrder);
        return refundOrderMapper.selectList(dto);
    }

    public Integer getCount(RefundOrder refundOrder) {
        RefundOrderDTO dto = new RefundOrderDTO();
        setDTO(dto, refundOrder);
        return refundOrderMapper.selectCount(dto);
    }

    private void setDTO(RefundOrderDTO dto, RefundOrder refundOrder) {
        if (refundOrder != null) {
            if (StringUtils.isNotBlank(refundOrder.getMchId())) {
                dto.setMchId(refundOrder.getMchId());
            }
            if (StringUtils.isNotBlank(refundOrder.getRefundOrderId())) {
                dto.setRefundOrderId(refundOrder.getRefundOrderId());
            }
            if (StringUtils.isNotBlank(refundOrder.getMchRefundNo())) {
                dto.setMchRefundNo(refundOrder.getMchRefundNo());
            }
            if (StringUtils.isNotBlank(refundOrder.getChannelOrderNo())) {
                dto.setChannelOrderNo(refundOrder.getChannelOrderNo());
            }
            if (refundOrder.getStatus() != null && refundOrder.getStatus() != -99) {
                dto.setStatus(refundOrder.getStatus());
            }
        }
    }
}
