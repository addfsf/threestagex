package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysRole implements Serializable {
    private Integer roleId;//角色id
    private String roleName;//角色名
    private String roleDesc;//角色定位
    private Integer ifVilid;
}
