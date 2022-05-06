package com.boot.dao;

import com.boot.domain.SongList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*歌单dao*/
@Mapper
@Repository
public interface SongListMapper {
    /**
     * 添加歌单
     * @param songList
     * @return
     */
    public int insert(SongList songList);

    /**
     * 修改歌单
     * @param songList
     * @return
     */
    public int update(SongList songList);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id);

    /**
     * 根据主键查询歌单对象
     * @param id
     * @return
     */
    public SongList selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单
     * @return
     */
    public List<SongList> allSongList();

    /**
     * 根据标题精确查询歌单列表
     * @param title
     * @return
     */
    public List<SongList> songListOfTitle(String title);

    /**
     * 根据标题模糊查询歌单
     * @param title
     * @return
     */
    public List<SongList> likeTitle(String title);

    /**
     * 根据风格模糊查询歌单
     * @param style
     * @return
     */
    public List<SongList> likeStyle(String style);

}
