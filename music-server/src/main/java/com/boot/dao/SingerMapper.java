package com.boot.dao;

import com.boot.domain.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*歌手dao*/
@Repository
@Mapper
public interface SingerMapper {

    /**
     * 添加歌手
     * @param singer
     * @return
     */
    public int insert(Singer singer);

    /**
     * 修改歌手
     * @param singer
     * @return
     */
    public int update(Singer singer);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id);

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
