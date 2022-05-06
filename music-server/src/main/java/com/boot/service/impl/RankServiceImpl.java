package com.boot.service.impl;

import com.boot.dao.RankMapper;
import com.boot.domain.Rank;
import com.boot.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*评价Service实现类*/
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    /**
     * 添加评价
     *
     * @param rank
     * @return
     */
    @Override
    public boolean insert(Rank rank) {
        return rankMapper.insert(rank)>0;
    }

    /**
     * 查询总分
     *
     * @param songListId 歌单id
     * @return
     */
    @Override
    public int selectScoreSum(Integer songListId) {
        return rankMapper.selectScoreSum(songListId);
    }

    /**
     * 查询评分总人数
     *
     * @param songListId 歌单id
     * @return
     */
    @Override
    public int selectRankNum(Integer songListId) {
        return rankMapper.selectRankNum(songListId);
    }

    /**
     * 计算平均分
     *
     * @param songListId 歌单id
     * @return
     */
    @Override
    public int rankOfSongListId(Integer songListId) {
        int rankNum=rankMapper.selectRankNum(songListId);
        if (rankNum==0) {
            return 5;
        }
        return rankMapper.selectScoreSum(songListId) / rankNum;
    }
}
