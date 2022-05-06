package com.boot.service.impl;

import com.boot.dao.SingerMapper;
import com.boot.domain.Singer;
import com.boot.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*歌手service实现类*/
@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;


    /**
     * 添加歌手
     *
     * @param singer
     * @return
     */
    @Override
    public boolean insert(Singer singer) {
        int insert = singerMapper.insert(singer);
        return insert>0; //判断是否插入了数据
    }

    /**
     * 修改歌手
     *
     * @param singer
     * @return
     */
    @Override
    public boolean update(Singer singer) {
        int update = singerMapper.update(singer);
        return update>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        int delete = singerMapper.delete(id);
        return delete>0;
    }

    /**
     * 根据主键查询歌手对象
     *
     * @param id
     * @return
     */
    @Override
    public Singer selectByPrimaryKey(Integer id) {
        Singer singer = singerMapper.selectByPrimaryKey(id);
        return singer;
    }

    /**
     * 查询所有歌手
     *
     * @return
     */
    @Override
    public List<Singer> allSinger() {
        List<Singer> singers = singerMapper.allSinger();
        return singers;
    }

    /**
     * 根据歌手名模糊查询歌手列表
     *
     * @param name
     * @return
     */
    @Override
    public List<Singer> singerOfName(String name) {
        List<Singer> singers = singerMapper.singerOfName(name);
        return singers;
    }

    /**
     * 根据性别查询歌手
     *
     * @param sex
     * @return
     */
    @Override
    public List<Singer> singerOfSex(Integer sex) {
        List<Singer> singers = singerMapper.singerOfSex(sex);
        return singers;
    }
}
