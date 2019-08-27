package com.qf.service;

import com.qf.pojo.SysRole;
import com.qf.pojo.User_Role;

import java.util.List;

public interface SysRoleService {
    /**
     * 加载所有角色信息
     * @return 角色List集合
     */
    public List<SysRole> loadAllRole();

    /**
     * 根据用户id加载对应的用户的角色信息
     * @param userId 用户id
     * @return 角色集合
     */
    public List<SysRole> loadRoleByUserId(int userId);

    /**
     * 将新的用户角色关系添加到关系表中
     * @param user_role
     * @return 添加结果
     */
    public boolean addDistribute(User_Role user_role);

    /**
     * 将新的用户角色关系添加到关系表中
     * @param uu
     * @return 添加结果
     */
    public boolean addDistributes(String uu);

    /**
     * 将新的用户关系删除
     * @param user_role
     * @return 删除结果
     */
    public boolean delDistribute(User_Role user_role);

    /**
     * 根据角色名查询角色id
     * @param roleName
     * @return roleId
     */
    public int getRoleIdByRoleName(String roleName);
}
