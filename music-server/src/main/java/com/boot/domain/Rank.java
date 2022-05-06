package com.boot.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/*歌曲评分基础*/
@Data
@ToString
public class Rank implements Serializable {
    private Integer id;
    private Integer songListId;  //歌单id
    private Integer consumerId;  //用户id
    private Integer score;       //评分
}
