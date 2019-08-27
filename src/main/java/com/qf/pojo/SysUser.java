package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysUser implements Serializable {
    private long userId;//用户id
    private String loginName;//用户名
    private String password;//密码
    private int state;//状态1表示用户有效，0表示被禁用户
    private String createTime;//用户的创建时间
    private String realName;//真实姓名
    private String email;//邮箱
}
