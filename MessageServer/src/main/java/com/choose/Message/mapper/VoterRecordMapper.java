package com.choose.Message.mapper;

import com.choose.Message.bean.VoterRecord;

public interface VoterRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoterRecord record);

    int insertSelective(VoterRecord record);

    VoterRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoterRecord record);

    int updateByPrimaryKey(VoterRecord record);
}