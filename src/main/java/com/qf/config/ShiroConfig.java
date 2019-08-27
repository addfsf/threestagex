package com.qf.config;

import com.qf.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//告诉你的系统，本类是一个配置类（用此对象初始化系统）请求拦截器
@Configuration
public class ShiroConfig {
    //创建shiro安全过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("defaultWebSecurityManager")
                    DefaultWebSecurityManager defaultWebSecurityManager
    ){
        ShiroFilterFactoryBean filterFactoryBean=new ShiroFilterFactoryBean();
//        给过滤器装配安全策略
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> map=new HashMap<>();
//        定义过滤规则map.put("b","a")必须有a权限才能访问b页面
        map.put("/main","authc");//必须登录才能访问
        map.put("/member","authc");//必须登录才能访问
        filterFactoryBean.setFilterChainDefinitionMap(map);//需要过滤连的定义
//        setLoginUrl("/login")设置没有登录的用户发出请求时登录的面
        filterFactoryBean.setLoginUrl("/loginview");
//        权限不足时提示页（没有权限时默认跳转的页面）
        filterFactoryBean.setUnauthorizedUrl("/unauth");

        return filterFactoryBean;
    }

    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm){

        DefaultWebSecurityManager webSecurityManager=new DefaultWebSecurityManager();
//        组装realm到SecurityManager中
        webSecurityManager.setRealm(myRealm);
        return webSecurityManager;
    }

    @Bean("myRealm")
    public MyRealm myRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm myRealm=new MyRealm();
        //装置凭证匹配器到realm
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        myRealm.setAuthorizationCachingEnabled(false);
        return myRealm;
    }

    //通过aop代理拦截权限设定
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    //设置注解拦截源码中的权限设定
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager
    ){

        AuthorizationAttributeSourceAdvisor  sourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return sourceAdvisor;
    }

    //创建凭证匹配器
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        //设置拼争过滤的算法模式
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        //设置加密算法
        matcher.setHashAlgorithmName("MD5");
        //设置shiro缓存的凭证编码
        matcher.setStoredCredentialsHexEncoded(true);
        //设置加密频次
        matcher.setHashIterations(1024);
        return matcher;
    }

    /**
     * 记住我cookie
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //设置此项以后，只能通过http访问不能通过xms访问
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * 设置记住我cookie管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        //定义记住我管理器
        CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
        //管理管理的cookie
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }
}
