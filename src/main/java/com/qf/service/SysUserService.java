package com.qf.service;

import com.qf.pojo.SysPermission;
import com.qf.pojo.SysUser;

import java.util.List;

public interface SysUserService {
    /**
     * 通过登录名插叙用户对象
     * @param loginName 参数是登录用户名
     * @return 返回值是Sysuer对象
     */
    public SysUser loadSysUserByLoginName(String loginName);

    /**
     * 通过登录名查询对应的权限信息
     * @param loginName 参数是登录用户名
     * @return 返回值是SysPermission的List集合
     */
    public List<SysPermission> loadSysPermissionByLoginName(String loginName);

    /**
     * 添加新用户，判断添加是否成功
     * @param sysUser 参数是新的用户对象
     * @return 返回值boolean值
     */
    public Boolean addNewUser(SysUser sysUser);

    /**
     * 获取当前用户表中最大的id值
     * @return 没有参数，返回值类型是int
     */
    public Integer getMaxId();

    /**
     * 获取最大页数
     * @return 最大页数
     */
    public Integer getMaxPageByRows(int rows);

    /**
     * 添加新的角色用户关系到tb_user_role关系表中
     * @param userId 用户的userId
     * @return boolean
     */
    public boolean addNewUserRole(int userId);

    /**
     * 修改用户密码
     * @param sysUser 修改后的用户对象
     * @return boolean
     */
    public boolean upPasswordByLoginName(SysUser sysUser);

    /**
     * 查询表中所有用户信息
     * @return 用户对象的list集合
     */
    public List<SysUser> loadAllSysUser();

    /**
     * 根据用户id查询对应的用户对象
     * @param userId
     * @return
     */
    public SysUser loadByUserId(int userId);

    /**
     * 根据用户id修改用户信息
     * @param sysUser 新的用户信息
     * @return 修改结果
     */
    public Boolean upSysUserByUserId(SysUser sysUser);


    /**
     * 根据页数和行数获取当前页面的用户集合
     * @param page 页数
     * @param rows 行数
     * @return 用户集合
     */
    public List<SysUser> loadByPage(int page,int rows);

    /**
     * 根据模糊查询字段查询结果
     * @return 查询到的结果集
     */
    public List<SysUser> mhSeacher(String mh);

    /**
     * 根据用户id删除对应的用户信息
     * @param userId 要删除的用户id
     * @return 删除结果
     */
    public Boolean deleteByUserId(int userId);

    /**
     * 根据用户id删除对应的关系表中的用户角色关系
     * @param userId
     * @return 删除结果
     */
    public Boolean deleteUserRoleByUserId(int userId);

    /**
     * 根据登录用户名查询对应用户的id信息
     * @param loginName 登录用户名
     * @return 查询出来的id
     */
    public Integer getUserIdByLoginName(String loginName);
}
