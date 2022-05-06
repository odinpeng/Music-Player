package com.boot.service.impl;

import com.boot.dao.SongListMapper;
import com.boot.domain.SongList;
import com.boot.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*歌单Service实现类*/
@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;


    /**
     * 添加歌单
     *
     * @param songList
     * @return
     */
    @Override
    public boolean insert(SongList songList) {
        return songListMapper.insert(songList)>0;
    }

    /**
     * 修改歌单
     *
     * @param songList
     * @return
     */
    @Override
    public boolean update(SongList songList) {
        return songListMapper.update(songList)>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songListMapper.delete(id)>0;
    }

    /**
     * 根据主键查询歌单对象
     *
     * @param id
     * @return
     */
    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单
     *
     * @return
     */
    @Override
    public List<SongList> allSongList() {
        return songListMapper.allSongList();
    }

    /**
     * 根据标题精确查询歌单列表
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> songListOfTitle(String title) {
        return songListMapper.songListOfTitle(title);
    }

    /**
     * 根据标题模糊查询歌单
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> likeTitle(String title) {
        return songListMapper.likeTitle(title);
    }

    /**
     * 根据风格模糊查询歌单
     *
     * @param style
     * @return
     */
    @Override
    public List<SongList> likeStyle(String style) {
        return songListMapper.likeStyle(style);
    }
}
