package com.kanghe.payment.platform.mapper;

import com.kanghe.payment.platform.dto.PayChannelDTO;
import com.kanghe.payment.platform.entity.PayChannel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayChannel record);

    int insertSelective(PayChannel record);

    PayChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayChannel record);

    int updateByPrimaryKey(PayChannel record);

    List<PayChannel> selectList(PayChannelDTO dto);

    int selectCount(PayChannelDTO dto);
}