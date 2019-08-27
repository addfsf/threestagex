package com.qf.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    /**
     * 登录首页
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(){
        return "../index";
    }

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/loginview")
    public String showLogin(){
        return "login";
    }

    /**
     * 管理员登录页面
     * admin登录时有权力访问的页面
     * @return
     */
    @RequiresPermissions(value = {"user_edit"})
    @RequestMapping("/main")
    public String showMain(){
        return "main";
    }



    /**
     * 权限不足时访问的页面
     * @return
     */
    @RequestMapping("/unauth")
    public String showUnauth(){
        return "unauth";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/reg")
    public String showReg(){
        return "reg";
    }

    /**
     * 跳转到找回密码页面
     * @return
     */
    @RequestMapping("/findPsw")
    public String showFindPsw(){
        return "find";
    }

    /**
     * 更新新的用户密码
     * @return
     */
    @RequestMapping("/upPsw")
    public String showupPsw(){
        return "upPsw";
    }

    /**
     * 跳转到新增用户页面
     * @return
     */
    @RequiresPermissions(value = {"user_edit"})
    @RequestMapping("/add")
    public String showAdd(){
        return "add";
    }


    /**
     * 跳转到关于我们页面
     * @return
     */
    @RequestMapping("/article")
    public String showArticle(){
        return "article";
    }



    /**
     * 跳转到登录后的首页
     * @return
     */
    @RequiresPermissions(value = {"user_forbidden"})
    @RequestMapping("/indexx")
    public String showIndexx(){
        return "indexx";
    }

    /**
     * 展示新手引导页面
     * @return
     */
    @RequestMapping("/problem")
    public String showProblem(){
        return "problem";
    }

}
