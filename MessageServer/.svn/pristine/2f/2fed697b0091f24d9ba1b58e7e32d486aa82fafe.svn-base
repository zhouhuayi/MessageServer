package com.choose.Message.mapper;

import com.choose.Message.bean.PublicMessage;
import com.choose.Message.pojo.MessagePojo;

public interface PublicMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PublicMessage record);

    int insertSelective(PublicMessage record);

    PublicMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PublicMessage record);

    int updateByPrimaryKeyWithBLOBs(PublicMessage record);

    int updateByPrimaryKey(PublicMessage record);
    
    /**
     * 批量添加公聊消息
     * 
     * @author 周化益
     * @param message 消息数据映射类
     * @return
     */
    public long bathAdd(MessagePojo message);
}