package com.boot.service.impl;

import com.boot.dao.AdminMapper;
import com.boot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 验证密码是否正确
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        int i = adminMapper.verifyPassword(username, password);
        /*i>0 说明查询到数据*/
        return i>0;
    }
}
