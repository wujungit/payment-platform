package com.kanghe.payment.platform.service;

import com.kanghe.payment.platform.dto.MchInfoDTO;
import com.kanghe.payment.platform.entity.MchInfo;
import com.kanghe.payment.platform.mapper.MchInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: MchInfoService
 */
@Service
public class MchInfoService {

    @Autowired
    private MchInfoMapper mchInfoMapper;

    public int addMchInfo(MchInfo mchInfo) {
        MchInfoDTO dto = new MchInfoDTO();
        dto.setOrderByClause("mch_id desc");
        dto.setOffset(0);
        dto.setLimit(1);
        List<MchInfo> mchInfos = mchInfoMapper.selectList(dto);
        String mchId = "10000000";
        if (!CollectionUtils.isEmpty(mchInfos)) {
            mchId = String.valueOf(Integer.parseInt(mchInfos.get(0).getMchId()) + 1);
        }
        mchInfo.setMchId(mchId);
        return mchInfoMapper.insertSelective(mchInfo);
    }

    public int updateMchInfo(MchInfo mchInfo) {
        return mchInfoMapper.updateByPrimaryKeySelective(mchInfo);
    }

    public MchInfo selectMchInfo(String mchId) {
        return mchInfoMapper.selectByPrimaryKey(mchId);
    }

    public List<MchInfo> getList(int offset, int limit, MchInfo mchInfo) {
        MchInfoDTO dto = new MchInfoDTO();
        dto.setOrderByClause("create_time desc");
        dto.setOffset(offset);
        dto.setLimit(limit);
        setCriteria(dto, mchInfo);
        return mchInfoMapper.selectList(dto);
    }

    public Integer getCount(MchInfo mchInfo) {
        MchInfoDTO dto = new MchInfoDTO();
        setCriteria(dto, mchInfo);
        return mchInfoMapper.selectCount(dto);
    }

    private void setCriteria(MchInfoDTO dto, MchInfo mchInfo) {
        if (mchInfo != null) {
            if (StringUtils.isNotBlank(mchInfo.getMchId())) {
                dto.setMchId(mchInfo.getMchId());
            }
            if (mchInfo.getType() != null && !"-99".equals(mchInfo.getType())) {
                dto.setType(mchInfo.getType());
            }
        }
    }
}
