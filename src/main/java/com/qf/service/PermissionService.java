package com.qf.service;

import com.qf.pojo.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 加载所有权限信息
     * @return 权限集合
     */
    public List<Permission> loadAllPermission();
}
