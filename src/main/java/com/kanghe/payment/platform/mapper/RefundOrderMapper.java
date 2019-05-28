package com.kanghe.payment.platform.mapper;

import com.kanghe.payment.platform.dto.RefundOrderDTO;
import com.kanghe.payment.platform.entity.RefundOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundOrderMapper {
    int deleteByPrimaryKey(String refundOrderId);

    int insert(RefundOrder record);

    int insertSelective(RefundOrder record);

    RefundOrder selectByPrimaryKey(String refundOrderId);

    int updateByPrimaryKeySelective(RefundOrder record);

    int updateByPrimaryKey(RefundOrder record);

    List<RefundOrder> selectList(RefundOrderDTO dto);

    int selectCount(RefundOrderDTO dto);
}