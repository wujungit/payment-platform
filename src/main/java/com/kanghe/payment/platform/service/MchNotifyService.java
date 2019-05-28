package com.kanghe.payment.platform.service;

import com.kanghe.payment.platform.dto.MchNotifyDTO;
import com.kanghe.payment.platform.entity.MchNotify;
import com.kanghe.payment.platform.mapper.MchNotifyMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: MchNotifyService
 */
@Service
public class MchNotifyService {

    @Autowired
    private MchNotifyMapper mchNotifyMapper;

    public MchNotify selectMchNotify(String orderId) {
        return mchNotifyMapper.selectByPrimaryKey(orderId);
    }

    public List<MchNotify> getList(int offset, int limit, MchNotify mchNotify) {
        MchNotifyDTO dto = new MchNotifyDTO();
        dto.setOrderByClause("create_time desc");
        dto.setOffset(offset);
        dto.setLimit(limit);
        setDTO(dto, mchNotify);
        return mchNotifyMapper.selectList(dto);
    }

    public Integer getCount(MchNotify mchNotify) {
        MchNotifyDTO dto = new MchNotifyDTO();
        setDTO(dto, mchNotify);
        return mchNotifyMapper.selectCount(dto);
    }

    private void setDTO(MchNotifyDTO dto, MchNotify mchNotify) {
        if (mchNotify != null) {
            if (StringUtils.isNotBlank(mchNotify.getMchId())) {
                dto.setMchId(mchNotify.getMchId());
            }
            if (StringUtils.isNotBlank(mchNotify.getOrderId())) {
                dto.setOrderId(mchNotify.getOrderId());
            }
            if (StringUtils.isNotBlank(mchNotify.getOrderType())) {
                dto.setOrderType(mchNotify.getOrderType());
            }
            if (StringUtils.isNotBlank(mchNotify.getMchOrderNo())) {
                dto.setMchOrderNo(mchNotify.getMchOrderNo());
            }
            if (mchNotify.getStatus() != null && mchNotify.getStatus() != -99) {
                dto.setStatus(mchNotify.getStatus());
            }
        }
    }
}
