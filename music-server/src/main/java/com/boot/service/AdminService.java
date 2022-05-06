package com.boot.service;

import org.springframework.stereotype.Service;

/*管理员Service*/
@Service
public interface AdminService {
    /**
     * 验证密码是否正确
     *
     * @param username
     * @param password
     * @return
     */
    public boolean verifyPassword(String username, String password);

}
