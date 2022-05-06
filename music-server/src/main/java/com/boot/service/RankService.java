package com.boot.service;

import com.boot.domain.Rank;
import org.springframework.stereotype.Service;

/*评价Service*/
@Service
public interface RankService {
    /**
     * 添加评价
     * @param rank
     * @return
     */
    public boolean insert(Rank rank);

    /**
     * 查询总分
     * @param songListId 歌单id
     * @return
     */
    public int selectScoreSum(Integer songListId);

    /**
     * 查询评分总人数
     * @param songListId 歌单id
     * @return
     */
    public int selectRankNum(Integer songListId);

    /**
     * 计算平均分
     * @param songListId 歌单id
     * @return
     */
    public int rankOfSongListId(Integer songListId);

}
