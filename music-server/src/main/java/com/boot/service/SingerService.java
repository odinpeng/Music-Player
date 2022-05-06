package com.boot.service;

import com.boot.domain.Singer;
import org.springframework.stereotype.Service;

import java.util.List;

/*歌手service*/
@Service
public interface SingerService {
    /**
     * 添加歌手
     * @param singer
     * @return
     */
    public boolean insert(Singer singer);

    /**
     * 修改歌手
     * @param singer
     * @return
     */
    public boolean update(Singer singer);

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询歌手对象
     * @param id
     * @return
     */
    public Singer selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     * @return
     */
    public List<Singer> allSinger();

    /**
     * 根据歌手名模糊查询歌手列表
     * @param name
     * @return
     */
    public List<Singer> singerOfName(String name);

    /**
     * 根据性别查询歌手
     * @param sex
     * @return
     */
    public List<Singer> singerOfSex(Integer sex);
}
