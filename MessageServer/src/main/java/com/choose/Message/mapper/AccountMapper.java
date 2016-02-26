package com.choose.Message.mapper;

import java.util.Map;

import com.choose.Message.bean.Account;
import com.choose.Message.bean.User;

public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    /**
     * 登录方法
     * 
     * @author 吴耀宗
     * @param accountMap 账号信息
     * @return
     */
     User userLogin(Map<String, Object> accountMap);
}