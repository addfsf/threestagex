package com.qf.mapper;

import com.qf.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {

    /**
     * 加载所有权限信息
     * @return 权限集合
     */
    public List<Permission> loadAllPermission();
}
