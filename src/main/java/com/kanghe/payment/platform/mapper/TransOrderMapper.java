package com.kanghe.payment.platform.mapper;

import com.kanghe.payment.platform.dto.TransOrderDTO;
import com.kanghe.payment.platform.entity.TransOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransOrderMapper {
    int deleteByPrimaryKey(String transOrderId);

    int insert(TransOrder record);

    int insertSelective(TransOrder record);

    TransOrder selectByPrimaryKey(String transOrderId);

    int updateByPrimaryKeySelective(TransOrder record);

    int updateByPrimaryKey(TransOrder record);

    List<TransOrder> selectList(TransOrderDTO dto);

    int selectCount(TransOrderDTO dto);
}