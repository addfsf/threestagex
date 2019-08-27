package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class ApplyMoney implements Serializable {
    private Integer id;//借款用户id
    private String loginName;//借款用户姓名
    private String moneyx;//借款数目
    private String times;//借款时长
    private String zfbNumber;//支付宝账号
    private String dbFunction;//担保方式
    private String jkyt;//借款用途
    private String jkms;//借款描述
    private Integer state;//借款申请的状态
    public ApplyMoney() {
    }

    public ApplyMoney(Integer id, String loginName, String moneyx, String times, String zfbNumber, String dbFunction, String jkyt, String jkms, Integer state) {
        this.id = id;
        this.loginName = loginName;
        this.moneyx = moneyx;
        this.times = times;
        this.zfbNumber = zfbNumber;
        this.dbFunction = dbFunction;
        this.jkyt = jkyt;
        this.jkms = jkms;
        this.state = state;
    }
}
