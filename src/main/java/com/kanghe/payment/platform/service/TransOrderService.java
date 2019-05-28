package com.kanghe.payment.platform.service;

import com.kanghe.payment.platform.dto.TransOrderDTO;
import com.kanghe.payment.platform.entity.TransOrder;
import com.kanghe.payment.platform.mapper.TransOrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: TransOrderService
 */
@Service
public class TransOrderService {

    @Autowired
    private TransOrderMapper transOrderMapper;

    public TransOrder selectTransOrder(String transOrderId) {
        return transOrderMapper.selectByPrimaryKey(transOrderId);
    }

    public List<TransOrder> getList(int offset, int limit, TransOrder transOrder) {
        TransOrderDTO dto = new TransOrderDTO();
        dto.setOrderByClause("create_time desc");
        dto.setOffset(offset);
        dto.setLimit(limit);
        setDTO(dto, transOrder);
        return transOrderMapper.selectList(dto);
    }

    public Integer getCount(TransOrder transOrder) {
        TransOrderDTO dto = new TransOrderDTO();
        setDTO(dto, transOrder);
        return transOrderMapper.selectCount(dto);
    }

    private void setDTO(TransOrderDTO dto, TransOrder transOrder) {
        if (transOrder != null) {
            if (StringUtils.isNotBlank(transOrder.getMchId())) {
                dto.setMchId(transOrder.getMchId());
            }
            if (StringUtils.isNotBlank(transOrder.getTransOrderId())) {
                dto.setTransOrderId(transOrder.getTransOrderId());
            }
            if (StringUtils.isNotBlank(transOrder.getMchTransNo())) {
                dto.setMchTransNo(transOrder.getMchTransNo());
            }
            if (StringUtils.isNotBlank(transOrder.getChannelOrderNo())) {
                dto.setChannelOrderNo(transOrder.getChannelOrderNo());
            }
            if (transOrder.getStatus() != null && transOrder.getStatus() != -99) {
                dto.setStatus(transOrder.getStatus());
            }
        }
    }
}
