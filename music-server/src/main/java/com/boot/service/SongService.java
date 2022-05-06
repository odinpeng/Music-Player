package com.boot.service;

import com.boot.domain.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/*歌曲Service*/
@Service
public interface SongService {

    /**
     * 添加歌曲
     * @param song
     * @return
     */
    public boolean insert(Song song);

    /**
     * 修改歌曲
     * @param song
     * @return
     */
    public boolean update(Song song);

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

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
     * 根据歌名精确查询歌曲列表
     * @param name
     * @return
     */
    public List<Song> songOfName(String name);

    /**
     * 根据歌名模糊查询歌曲列表
     * @param name
     * @return
     */
    public List<Song> likeSongOfName(String name);


    /**
     * 根据歌手id查询歌曲列表
     * @param singerId
     * @return
     */
    public List<Song> songOfSingerId(Integer singerId);



}
