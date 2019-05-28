package com.kanghe.payment.platform.mapper;

import com.kanghe.payment.platform.dto.PayOrderDTO;
import com.kanghe.payment.platform.entity.PayOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayOrderMapper {
    int deleteByPrimaryKey(String payOrderId);

    int insert(PayOrder record);

    int insertSelective(PayOrder record);

    PayOrder selectByPrimaryKey(String payOrderId);

    int updateByPrimaryKeySelective(PayOrder record);

    int updateByPrimaryKey(PayOrder record);

    List<PayOrder> selectList(PayOrderDTO dto);

    int selectCount(PayOrderDTO dto);
}