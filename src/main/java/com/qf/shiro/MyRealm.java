package com.qf.shiro;


import com.qf.pojo.SysPermission;
import com.qf.pojo.SysUser;
import com.qf.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    //注入业务实现
    @Autowired
    public SysUserService sysUserServiceImpl;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从身份信息中获取当前用户对应的信息
        //获取当前操作的主体对象
        Object object=principalCollection.getPrimaryPrincipal();
        if (object!=null){
            //获取当前登录用户的用户名
            String loginName=(String)object;
            //获取当前登录用户的权限信息
            List<SysPermission> permissions=sysUserServiceImpl.loadSysPermissionByLoginName(loginName);
            //权限去重
            Set<String> p=new HashSet<>();
            for (SysPermission s:permissions){
                String x=s.getPerName();
                p.add(x);
            }
            //授权
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            info.addStringPermissions(p);
            return info;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取当前登录用户的用户名
        Object object=token.getPrincipal();
        if (object!=null){
            String loginName=(String)object;
            //获取当前登录用户的信息
            SysUser sysUser=sysUserServiceImpl.loadSysUserByLoginName(loginName);
            //加密加盐
            ByteSource source=ByteSource.Util.bytes(loginName);
            //int source=(int) sysUser.getUserId();
            //将查询的用户信息结果返回给controller
            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(loginName,sysUser.getPassword(),source,getName());
            return info;
        }
        return null;
    }
}
