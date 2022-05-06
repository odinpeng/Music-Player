package com.boot.service.impl;

import com.boot.dao.CollectMapper;
import com.boot.domain.Collect;
import com.boot.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*收藏service实现类*/
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;
    /**
     * 添加收藏
     *
     * @param collect
     * @return
     */
    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect)>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return collectMapper.delete(id)>0;
    }

    /**
     * 根据用户id和歌曲id删除收藏
     *
     * @param userId
     * @param songId
     * @return
     */
    @Override
    public boolean deleteByUserIdSongId(Integer userId, Integer songId) {
        return collectMapper.deleteByUserIdSongId(userId,songId)>0;
    }

    /**
     * 查询所有收藏
     *
     * @return
     */
    @Override
    public List<Collect> allCollect() {
        return collectMapper.allCollect();
    }

    /**
     * 根据用户id查询该用户的收藏列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Collect> collectOfUserId(Integer userId) {
        return collectMapper.collectOfUserId(userId);
    }

    /**
     * 查询某用户是否收藏了某歌曲
     *
     * @param userId
     * @param songId
     * @return
     */
    @Override
    public boolean existSongId(Integer userId, Integer songId) {
        return collectMapper.existSongId(userId,songId)>0;
    }
}
