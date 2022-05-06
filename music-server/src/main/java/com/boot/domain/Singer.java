package com.boot.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/*歌手基础*/
@Data
@ToString
public class Singer implements Serializable { //为了前后台传输，实现序列化
    private Integer id;
    private String name;
    private Byte sex;
    private String pic; //头像
    private Date birth;
    private String location; //地区
    private String introduction; //简介
}
