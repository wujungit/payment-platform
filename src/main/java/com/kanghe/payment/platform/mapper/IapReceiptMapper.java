package com.kanghe.payment.platform.mapper;

import com.kanghe.payment.platform.entity.IapReceipt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IapReceiptMapper {
    int deleteByPrimaryKey(String payOrderId);

    int insert(IapReceipt record);

    int insertSelective(IapReceipt record);

    IapReceipt selectByPrimaryKey(String payOrderId);

    int updateByPrimaryKeySelective(IapReceipt record);

    int updateByPrimaryKeyWithBLOBs(IapReceipt record);

    int updateByPrimaryKey(IapReceipt record);

    List<IapReceipt> selectList();

    int selectCount();
}