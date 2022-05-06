package com.boot.service;

import com.boot.domain.Consumer;
import org.springframework.stereotype.Service;

import java.util.List;

/*用户service*/
@Service
public interface ConsumerService {
    /**
     * 添加用户
     * @param consumer
     * @return
     */
    public boolean insert(Consumer consumer);

    /**
     * 修改用户
     * @param consumer
     * @return
     */
    public boolean update(Consumer consumer);

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询用户对象
     * @param id
     * @return
     */
    public Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    public List<Consumer> allConsumer();

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    public Consumer getByUserName(String username);

    /**
     * 验证密码
     * @param username
     * @param password
     * @return
     */
    public boolean verifyPassword(String username, String password);
}
