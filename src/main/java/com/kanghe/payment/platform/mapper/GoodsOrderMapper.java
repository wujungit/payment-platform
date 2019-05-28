package com.kanghe.payment.platform.mapper;

import com.kanghe.payment.platform.entity.GoodsOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsOrderMapper {
    int deleteByPrimaryKey(String goodsOrderId);

    int insert(GoodsOrder record);

    int insertSelective(GoodsOrder record);

    GoodsOrder selectByPrimaryKey(String goodsOrderId);

    int updateByPrimaryKeySelective(GoodsOrder record);

    int updateByPrimaryKey(GoodsOrder record);

    List<GoodsOrder> selectList();

    int selectCount();
}