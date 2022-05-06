package com.boot.dao;

import com.boot.domain.Song;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*歌曲dao*/
@Mapper
@Repository
public interface SongMapper {

    /**
     * 添加歌曲
     * @param song
     * @return
     */
    public int insert(Song song);

    /**
     * 修改歌曲
     * @param song
     * @return
     */
    public int update(Song song);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id);

    /**
     * 根据主键查询歌曲对象
     * @param id
     * @return
     */
    public Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     * @return
     */
    public List<Song> allSong();

    /**
     * 根据歌名模糊查询歌曲列表
     * @param name
     * @return
     */
    public List<Song> likeSongOfName(String name);

    /**
     * 根据歌名精确查询歌曲列表
     * @param name
     * @return
     */
    public List<Song> songOfName(String name);


    /**
     * 根据歌手id查询歌曲列表
     * @param singerId
     * @return
     */
    public List<Song> songOfSingerId(Integer singerId);

}
