package com.boot.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/*评论基础*/
@Data
@ToString
public class Comment {
    private Integer id;
    private Integer userId;     //用户id
    private Byte type;          //评论类型，1：歌单，2：歌曲
    private Integer songId;     //歌曲id
    private Integer songListId; //歌单id
    private String content;     //评论内容
    private Date createTime;    //评论时间
    private Integer up;         //评论点赞数
}
