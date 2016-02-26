package com.choose.Message.mapper;

import com.choose.Message.bean.Radio;

public interface RadioMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Radio record);

    int insertSelective(Radio record);

    Radio selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Radio record);

    int updateByPrimaryKey(Radio record);
}