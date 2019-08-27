package com.qf.mapper;

import com.qf.pojo.SysPermission;
import com.qf.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

    /**
     * 通过登录名插叙用户对象
     * @param loginName 参数是登录用户名
     * @return 返回值是Sysuer对象
     */
    public SysUser loadSysUserByLoginName(String loginName);

    /**
     * 通过登录名查询对应的权限信息
     * @param loginName 参数是登录用户名
     * @return 返回值类型是SysPermission的List集合
     */
    public List<SysPermission> loadSysPermissionByLoginName(String loginName);

    /**
     * 注册新用户，将新的用户信息存储到数据库中
     * @param sysUser 参数是新的user对象
     * @return 返回值类型是添加结果Integer值
     */
    public Integer addNewUser(SysUser sysUser);

    /**
     * 获取当前条件下最大的id值
     * @return 无参数，返回值类型是Integer值
     */
    public Integer getMaxId();

    /**
     * 添加新的角色用户关系到tb_user_role关系表中
     * @param userId 输入参数是新用户的userId
     * @return Integer
     */
    public Integer addNewUserRole(int userId);

    /**
     * 修改用户密码信息
     * @param sysUser 修改后的用户对象
     * @return integer
     */
    public Integer upPasswordByLoginName(SysUser sysUser);

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
    public Integer upSysUserByUserId(SysUser sysUser);

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
    public Integer deleteByUserId(int userId);

    /**
     * 根据用户id删除对应的关系表中的用户角色关系
     * @param userId
     * @return 删除结果
     */
    public Integer deleteUserRoleByUserId(int userId);

    /**
     * 获取最大行数
     * @return
     */
    public Integer getCount();

    /**
     * 根据登录用户名查询对应用户的id信息
     * @param loginName 登录用户名
     * @return 查询出来的id
     */
    public Integer getUserIdByLoginName(String loginName);
}
