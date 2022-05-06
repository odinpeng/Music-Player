package com.boot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/*管理员dao*/
@Repository
@Mapper
public interface AdminMapper {
    /**
     * 验证密码是否正确
     * @param username
     * @param password
     * @return
     */
    public int verifyPassword(String username,String password);

}
