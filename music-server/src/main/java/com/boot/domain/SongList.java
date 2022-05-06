package com.boot.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/*歌单基础*/
@Data
@ToString
public class SongList implements Serializable {
    private Integer id;
    private String title; //标题
    private String pic; //图片
    private String introduction; //简介
    private String style; //风格
}
