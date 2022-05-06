package com.boot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/*管理员基础*/
@Data
@ToString
public class Admin implements Serializable { //为了前后台传输，实现序列化
    private Integer id;
    private String name;
    private String password;
}
