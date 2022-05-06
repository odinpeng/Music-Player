package com.boot.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/*歌曲基础*/
@Data
@ToString
public class Song implements Serializable { //为了前后台传输，实现序列化
    private Integer id;
    private Integer singerId; //歌手id
    private String name;
    private String introduction; //简介
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private String pic; //歌曲图片
    private String lyric; //歌词
    private String url;
}
