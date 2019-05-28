package com.kanghe.payment.platform.mapper;

import com.kanghe.payment.platform.dto.MchInfoDTO;
import com.kanghe.payment.platform.entity.MchInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchInfoMapper {
    int deleteByPrimaryKey(String mchId);

    int insert(MchInfo record);

    int insertSelective(MchInfo record);

    MchInfo selectByPrimaryKey(String mchId);

    int updateByPrimaryKeySelective(MchInfo record);

    int updateByPrimaryKey(MchInfo record);

    List<MchInfo> selectList(MchInfoDTO dto);

    int selectCount(MchInfoDTO dto);
}