package com.boot.service.impl;

import com.boot.dao.ConsumerMapper;
import com.boot.domain.Consumer;
import com.boot.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*用户service实现类*/
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    /**
     * 添加用户
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean insert(Consumer consumer) {
        return consumerMapper.insert(consumer)>0;
    }

    /**
     * 修改用户
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean update(Consumer consumer) {
        return consumerMapper.update(consumer)>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return consumerMapper.delete(id)>0;
    }

    /**
     * 根据主键查询用户对象
     *
     * @param id
     * @return
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<Consumer> allConsumer() {
        return consumerMapper.allConsumer();
    }

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    @Override
    public Consumer getByUserName(String username) {
        return consumerMapper.getByUserName(username);
    }

    /**
     * 验证密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return consumerMapper.verifyPassword(username,password)>0;
    }
}
