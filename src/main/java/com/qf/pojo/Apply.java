package com.qf.pojo;

import lombok.Data;

@Data
public class Apply {
    private Integer id;//实名认证信息的id
    private String loginName;//登录名
    private String realName;//真实姓名
    private String number;//实名认证的身份证号码
    private String adress;//实名认证用户的当前地址
    private String page;//真人照片
    private String state;//实名认证的状态
}
