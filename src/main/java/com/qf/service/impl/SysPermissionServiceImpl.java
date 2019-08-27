package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.mapper.SysPermissionMapper;
import com.qf.pojo.Role_Permission;
import com.qf.pojo.SysPermission;
import com.qf.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 加载所有权限信息
     * @return
     */
    @Override
    public List<SysPermission> loadAllPermissions() {
        List<SysPermission> all=sysPermissionMapper.loadAllPermissions();
        return all;
    }

    /**
     * 根据角色id查询当前对应的权限信息
     * @return 对应角色拥有的权限信息
     */
    @Override
    public List<SysPermission> loadSyspermissionByRid(int roleId) {
        List<SysPermission> as=sysPermissionMapper.loadSyspermissionByRid(roleId);
        return as;
    }

    /**
     * 根据权限名查询权限id
     * @param perName 权限名
     * @return 查询到的权限id
     */
    @Override
    public Integer loadIdByPerName(String perName) {
        return sysPermissionMapper.loadIdByPerName(perName);
    }

    /**
     * 将新的角色权限关系添加到关系表中
     * @param role_permission 新的角色权限关系
     * @return 添加结果
     */
    @Override
    public Boolean addNewRolePermission(Role_Permission role_permission) {
        return sysPermissionMapper.addNewRolePermission(role_permission)>0? true:false;
    }

    /**
     * 将对应的角色权限关系数据从关系表删除
     * @param role_permission 要删除的角色权限关系信息
     * @return 删除结果
     */
    @Override
    public Boolean reduceRolePermission(Role_Permission role_permission) {
        return sysPermissionMapper.reduceRolePermission(role_permission)>0? true:false;
    }

    /**
     * 根据行数获取最大的页数
     * @param rows 行数
     * @return 最大页数
     */
    @Override
    public Integer getMaxPage(int rows) {
        return sysPermissionMapper.getCount()%rows==0? sysPermissionMapper.getCount()/rows:sysPermissionMapper.getCount()/rows+1;
    }

    /**
     * 根据页数和行数加载当前页要展示的权限数据信息
     * @param page 页数
     * @param rows 行数
     * @return 查询到的对应的权限信息集合
     */
    @Override
    public List<SysPermission> loadPermissions(int page, int rows) {
        PageHelper.startPage(page,rows);
        return sysPermissionMapper.loadAllPermissions();
    }
}
