package com.qf.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysPermission implements Serializable {
    private int id;//权限id
    private int pid;//
    private String perName;//权限名
    private String menuName;//菜单名
    private String menuType;//菜单分类
    private String menuUrl;//菜单接口url
    private String menuCode;//菜单编号
    private String parentCode;//父编号
    private String perDesc;//权限描述
    private String ifVilid;//是否有效
    private String icon;//图标
    private String checked="false";//复选框的状态

}
