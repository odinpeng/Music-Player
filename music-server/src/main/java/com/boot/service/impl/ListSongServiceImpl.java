package com.boot.service.impl;

import com.boot.dao.ListSongMapper;
import com.boot.domain.ListSong;
import com.boot.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*歌单的歌曲service实现类*/
@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    /**
     * 添加歌曲
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean insert(ListSong listSong) {
        return listSongMapper.insert(listSong)>0;
    }

    /**
     * 修改歌曲
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean update(ListSong listSong) {
        return listSongMapper.update(listSong)>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return listSongMapper.delete(id)>0;
    }

    /**
     * 根据歌曲id和歌单id删除歌单中的歌曲
     *
     * @param songId     歌曲id
     * @param songListId 歌单id
     * @return
     */
    @Override
    public boolean deleteBySongIdAndSongListId(Integer songId, Integer songListId) {
        return listSongMapper.deleteBySongIdAndSongListId(songId,songListId)>0;
    }

    /**
     * 根据主键查询歌曲对象
     *
     * @param id
     * @return
     */
    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌曲
     *
     * @return
     */
    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.allListSong();
    }

    /**
     * 根据歌单id查询
     *
     * @param songListId
     * @return
     */
    @Override
    public List<ListSong> listSongOfSongListId(Integer songListId) {
        return listSongMapper.listSongOfSongListId(songListId);
    }
}
