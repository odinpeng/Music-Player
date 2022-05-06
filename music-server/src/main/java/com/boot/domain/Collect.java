package com.boot.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/*收藏基础*/
@Data
@ToString
public class Collect implements Serializable {
    private Integer id;
    private Integer userId;
    private Byte type;
    private Integer songId;
    private Integer songListId;
    private Date createTime;
}
