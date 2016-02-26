package com.choose.Message.mapper;

import com.choose.Message.bean.PrivateMessage;
import com.choose.Message.pojo.MessagePojo;

public interface PrivateMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrivateMessage record);

    int insertSelective(PrivateMessage record);

    PrivateMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrivateMessage record);

    int updateByPrimaryKeyWithBLOBs(PrivateMessage record);

    int updateByPrimaryKey(PrivateMessage record);
    
    /**
     * 批量添加私聊消息
     * 
     * @author 周化益
     * @param message 消息数据映射类
     * @return
     */
    public long bathAdd(MessagePojo message);
}