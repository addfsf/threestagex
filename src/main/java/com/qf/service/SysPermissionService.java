package com.qf.service;

import com.qf.pojo.Role_Permission;
import com.qf.pojo.SysPermission;

import java.util.List;

public interface SysPermissionService {
    /**
     * 加载所有的权限信息
     * @return 权限列表
     */
    public List<SysPermission> loadAllPermissions();

    /**
     * 根据角色id查询出对应的权限信息
     * @return 当前角色包含的权限列表
     */
    public List<SysPermission> loadSyspermissionByRid(int roleId);

    /**
     * 根据权限名查询权限id
     * @param perName 权限名
     * @return 查询到的权限id
     */
    public Integer loadIdByPerName(String perName);

    /**
     * 将新的权限角色信息添加到
     * @param role_permission 新的角色权限关系
     * @return 添加结果
     */
    public Boolean addNewRolePermission(Role_Permission role_permission);

    /**
     * 删除对应的角色权限关系信息
     * @param role_permission 要删除的角色权限关系信息
     * @return 删除的结果
     */
    public Boolean reduceRolePermission(Role_Permission role_permission);

    /**
     * 根据行数获取最大的页数
     * @param rows 行数
     * @return 页数
     */
    public Integer getMaxPage(int rows);

    /**
     * 根据页数和行数获取当前页面要展示的权限信息集合
     * @param page 页数
     * @param rows 行数
     * @return 权限集合
     */
    public List<SysPermission> loadPermissions(int page,int rows);
}
