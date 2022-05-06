package com.boot.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/*歌单里的歌曲基础*/
@Data
@ToString
public class ListSong implements Serializable { //实现序列化
    private Integer id;
    private Integer songId;
    private Integer songListId; //歌单id
}
