package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.mapper.SysUserMapper;
import com.qf.pojo.SysPermission;
import com.qf.pojo.SysUser;
import com.qf.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据登录名加载用户
     * @param loginName 参数是登录用户名
     * @return 返回值类型是SysUser
     */
    @Override
    public SysUser loadSysUserByLoginName(String loginName) {
        SysUser user=sysUserMapper.loadSysUserByLoginName(loginName);
        return user;
    }

    /**
     * 通过登录名查询对应的权限信息
     * @param loginName 参数是登录用户名
     * @return 返回值是SysPermission的List集合
     */
    @Override
    public List<SysPermission> loadSysPermissionByLoginName(String loginName) {
        List<SysPermission> permissions=sysUserMapper.loadSysPermissionByLoginName(loginName);
        return permissions;
    }

    /**
     * 添加新用户，判断添加是否成功
     * @param sysUser 参数是新的用户对象
     * @return 添加结果
     */
    @Override
    public Boolean addNewUser(SysUser sysUser) {
        boolean result=false;
        int state=0;
        state=sysUserMapper.addNewUser(sysUser);
       result= state>0? true:false;
        return result;
    }

    /**
     * 获取当前用户表中最大的id值
     * @return 没有参数，返回值类型是int
     */
    @Override
    public Integer getMaxId() {
        int max=0;
        max=sysUserMapper.getMaxId();
        return max;
    }

    /**
     * 根据行数获取当前的最大页数
     * @param rows 行数
     * @return 最大页数
     */
    @Override
    public Integer getMaxPageByRows(int rows) {
        return sysUserMapper.getCount()%rows==0? sysUserMapper.getCount()/rows :sysUserMapper.getCount()/rows+1;
    }

    /**
     * 添加新的角色用户关系到tb_user_role关系表中
     * @param userId 用户的userId
     * @return boolean
     */
    @Override
    public boolean addNewUserRole(int userId) {
        int result=0;
        result=sysUserMapper.addNewUserRole(userId);
        return result>0? true:false;
    }

    /**
     * 修改用户密码
     * @param sysUser 修改后的用户对象
     * @return boolean
     */
    @Override
    public boolean upPasswordByLoginName(SysUser sysUser) {
        int state=0;
        state=sysUserMapper.upPasswordByLoginName(sysUser);
        return state>0? true:false;
    }

    /**
     * 查询表中所有用户信息
     * @return 用户对象的list集合
     */
    @Override
    public List<SysUser> loadAllSysUser() {
        List<SysUser> all=sysUserMapper.loadAllSysUser();
        return all;
    }

    /**
     * 根据用户id查询对应的用户对象
     * @param userId
     * @return
     */
    @Override
    public SysUser loadByUserId(int userId) {
        SysUser user=sysUserMapper.loadByUserId(userId);
        return user;
    }

    /**
     * 根据用户id修改用户信息
     * @param sysUser 新的用户信息
     * @return 修改结果
     */
    @Override
    public Boolean upSysUserByUserId(SysUser sysUser) {
        int state=0;
        state=sysUserMapper.upSysUserByUserId(sysUser);
        return state>0 ? true:false;
    }

    /**
     * 根据行数和页数加载对应的用户列表信息
     * @param page 页数
     * @param rows 行数
     * @return 用户列表
     */
    @Override
    public List<SysUser> loadByPage(int page, int rows) {
        PageHelper.startPage(page,rows);
        return sysUserMapper.loadAllSysUser();
    }

    /**
     * 根据输入的字段信息模糊查询
     * @param mh 输入的字段信息
     * @return 查询到的结果集
     */
    @Override
    public List<SysUser> mhSeacher(String mh) {
        return sysUserMapper.mhSeacher(mh);
    }

    /**
     * 根据用户id删除对应的信息
     * @param userId 要删除的用户id
     * @return 删除结果
     */
    @Override
    public Boolean deleteByUserId(int userId) {
        return sysUserMapper.deleteByUserId(userId)>0? true:false;
    }

    /**
     * 根据用户id删除对应的用户角色关系表数据
     * @param userId 用户id
     * @return 删除结果
     */
    @Override
    public Boolean deleteUserRoleByUserId(int userId) {
        return sysUserMapper.deleteUserRoleByUserId(userId)>0? true:false;
    }

    /**
     * 根据登录用户名查询对应的id
     * @param loginName 登录用户名
     * @return 查询到的用户id
     */
    @Override
    public Integer getUserIdByLoginName(String loginName) {
        return sysUserMapper.getUserIdByLoginName(loginName);
    }
}
