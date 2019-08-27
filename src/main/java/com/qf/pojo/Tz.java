package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Tz implements Serializable {
    private Integer id;//id
    private String itemName;//项目名称
    private String rewardYear;//年收益
    private String backFunction;//还款方式
    private String residueDay;//剩余天数
    private String protectTeam;//保障机构
    private String money;//募集资金
    private String tzState;//投资状态
}
