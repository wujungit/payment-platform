package com.kanghe.payment.platform.mapper;

import com.kanghe.payment.platform.dto.MchNotifyDTO;
import com.kanghe.payment.platform.entity.MchNotify;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchNotifyMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(MchNotify record);

    int insertSelective(MchNotify record);

    MchNotify selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(MchNotify record);

    int updateByPrimaryKey(MchNotify record);

    List<MchNotify> selectList(MchNotifyDTO dto);

    int selectCount(MchNotifyDTO dto);
}