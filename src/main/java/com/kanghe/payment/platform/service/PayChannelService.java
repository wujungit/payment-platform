package com.kanghe.payment.platform.service;

import com.kanghe.payment.platform.dto.PayChannelDTO;
import com.kanghe.payment.platform.entity.PayChannel;
import com.kanghe.payment.platform.mapper.PayChannelMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: PayChannelService
 */
@Service
public class PayChannelService {

    @Autowired
    private PayChannelMapper payChannelMapper;

    public int addPayChannel(PayChannel payChannel) {
        return payChannelMapper.insertSelective(payChannel);
    }

    public int updatePayChannel(PayChannel payChannel) {
        return payChannelMapper.updateByPrimaryKeySelective(payChannel);
    }

    public PayChannel selectPayChannel(String channelId, String mchId) {
        PayChannelDTO dto = new PayChannelDTO();
        dto.setChannelId(channelId);
        dto.setMchId(mchId);
        List<PayChannel> payChannelList = payChannelMapper.selectList(dto);
        if (CollectionUtils.isEmpty(payChannelList)) {
            return null;
        }
        return payChannelList.get(0);
    }

    public PayChannel selectPayChannel(int id) {
        return payChannelMapper.selectByPrimaryKey(id);
    }

    public List<PayChannel> getList(int offset, int limit, PayChannel payChannel) {
        PayChannelDTO dto = new PayChannelDTO();
        dto.setOrderByClause("mch_id asc, channel_id asc, create_time desc");
        dto.setOffset(offset);
        dto.setLimit(limit);
        setDTO(dto, payChannel);
        return payChannelMapper.selectList(dto);
    }

    public Integer getCount(PayChannel payChannel) {
        PayChannelDTO dto = new PayChannelDTO();
        setDTO(dto, payChannel);
        return payChannelMapper.selectCount(dto);
    }

    private void setDTO(PayChannelDTO dto, PayChannel payChannel) {
        if (payChannel != null) {
            if (StringUtils.isNotBlank(payChannel.getMchId())) {
                dto.setMchId(payChannel.getMchId());
            }
            if (StringUtils.isNotBlank(payChannel.getChannelId())) {
                dto.setChannelId(payChannel.getChannelId());
            }
        }
    }
}
