package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class TzIdLoginName implements Serializable {
    private Integer tid;
    private String loginName;
}
