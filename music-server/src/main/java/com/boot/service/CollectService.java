package com.boot.service;

import com.boot.domain.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/*收藏Service*/
@Service
public interface CollectService {
    /**
     * 添加收藏
     *
     * @param collect
     * @return
     */
    public boolean insert(Collect collect);


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据用户id和歌曲id删除收藏
     * @param userId
     * @param songId
     * @return
     */
    public boolean deleteByUserIdSongId(Integer userId,Integer songId);


    /**
     * 查询所有收藏
     *
     * @return
     */
    public List<Collect> allCollect();

    /**
     * 根据用户id查询该用户的收藏列表
     *
     * @param userId
     * @return
     */
    public List<Collect> collectOfUserId(Integer userId);

    /**
     * 查询某用户是否收藏了某歌曲
     *
     * @param userId
     * @param songId
     * @return
     */
    public boolean existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);
}
