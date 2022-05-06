package com.boot.service;

import com.boot.domain.ListSong;
import org.springframework.stereotype.Service;

import java.util.List;

/*歌单的歌曲service*/
@Service
public interface ListSongService {
    /**
     * 添加歌曲
     * @param listSong
     * @return
     */
    public boolean insert(ListSong listSong);

    /**
     * 修改歌曲
     * @param listSong
     * @return
     */
    public boolean update(ListSong listSong);

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据歌曲id和歌单id删除歌单中的歌曲
     * @param songId 歌曲id
     * @param songListId 歌单id
     * @return
     */
    public boolean deleteBySongIdAndSongListId(Integer songId, Integer songListId);

    /**
     * 根据主键查询歌曲对象
     * @param id
     * @return
     */
    public ListSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     * @return
     */
    public List<ListSong> allListSong();

    /**
     * 根据歌单id查询
     * @param songListId
     * @return
     */
    public List<ListSong> listSongOfSongListId(Integer songListId);
}
