package com.boot.dao;

import com.boot.domain.Rank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*评价评分dao层*/
@Mapper
@Repository
public interface RankMapper {
    /**
     * 添加评价
     * @param rank
     * @return
     */
    public int insert(Rank rank);

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


}
