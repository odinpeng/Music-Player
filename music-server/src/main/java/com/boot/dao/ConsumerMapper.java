package com.boot.dao;

import com.boot.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/*用户dao*/
@Mapper
@Repository
public interface ConsumerMapper {
    /**
     * 添加用户
     * @param consumer
     * @return
     */
    public int insert(Consumer consumer);

    /**
     * 修改用户
     * @param consumer
     * @return
     */
    public int update(Consumer consumer);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id);

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
    public int verifyPassword(String username, String password);
}
