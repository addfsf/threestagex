package com.qf.service.impl;

import com.qf.mapper.PermissionMapper;
import com.qf.pojo.Permission;
import com.qf.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 加载所有权限信息
     * @return 权限集合
     */
    @Override
    public List<Permission> loadAllPermission() {
        List<Permission> permissions=permissionMapper.loadAllPermission();
        return permissions;
    }
}
