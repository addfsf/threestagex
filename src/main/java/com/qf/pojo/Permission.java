package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Permission implements Serializable {
    private int id;//权限id
    private int pId;//父节点id
    private String pname;//权限名
    private String url;//链接
    private String icon;//图标
    private String checked="false";//复选框的状态
}
