package com.boot.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/*用户基础*/
@Data
@ToString
public class Consumer implements Serializable { //实现序列化
    private Integer id;
    private String username;
    private String password;
    private Byte sex;
    private String phoneNum;
    private String email;
    private Date birth;
    private String introduction; //签名
    private String location;
    private String avator; //头像
    private Date createTime;
    private Date updateTime;
}
