package com.qf.service.impl;

import com.qf.mapper.SysRoleMapper;
import com.qf.pojo.SysRole;
import com.qf.pojo.User_Role;
import com.qf.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 加载所有角色信息
     * @return 角色集合
     */
    @Override
    public List<SysRole> loadAllRole() {
        List<SysRole> all=sysRoleMapper.loadAllRole();
        return all;
    }

    /**
     * 根据用户id查询对应用户的角色信息
     * @param userId 用户id
     * @return 角色信息集合
     */
    @Override
    public List<SysRole> loadRoleByUserId(int userId) {
        List<SysRole> roleList=sysRoleMapper.loadRoleByUserId(userId);
        return roleList;
    }

    /**
     * 将新的用户角色关系添加到关系表中
     * @param user_role
     * @return 修改结果
     */
    @Override
    public boolean addDistribute(User_Role user_role) {
        return sysRoleMapper.addDistribute(user_role)>0? true:false;
    }

    @Override
    public boolean addDistributes(String uu) {
        return sysRoleMapper.addDistributes(uu)>0? true:false;
    }

    /**
     * 将新的用户角色关系从关系表删除
     * @param user_role
     * @return 删除结果
     */
    @Override
    public boolean delDistribute(User_Role user_role) {
        return sysRoleMapper.delDistribute(user_role)>0? true:false;
    }

    /**
     * 根据角色名查询角色id
     * @param roleName
     * @return roleId
     */
    @Override
    public int getRoleIdByRoleName(String roleName) {
        return sysRoleMapper.getRoleIdByRoleName(roleName);
    }
}
