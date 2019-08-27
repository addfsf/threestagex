package com.qf.controller;

import com.qf.pojo.Gz_Tz;
import com.qf.pojo.SysUser;
import com.qf.pojo.Tz;
import com.qf.pojo.TzIdLoginName;
import com.qf.service.SysUserService;
import com.qf.service.TzService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TzController {

    @Autowired
    private TzService tzServiceImpl;

    @Autowired
    private SysUserService sysUserServiceImpl;

    /**
     * 根据用户名和取消关注的id删除对应的关系表信息
     * @param id 取消关注的业务id
     * @param loginName 当前登录的用户名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping("/qxgz")
    public Boolean showQxgzResult(Integer id,String loginName){
        TzIdLoginName tzIdLoginName=new TzIdLoginName();
        tzIdLoginName.setTid(id);
        tzIdLoginName.setLoginName(loginName);
        boolean state=tzServiceImpl.delGzUserByLoginName(tzIdLoginName);
        return state;
    }

    private String curUserName="x";
    /**
     * 跳转到我要投资页面
     * @return
     */
    @RequiresPermissions(value = {"user_forbidden"})
    @RequestMapping("/invest")
    public String showInvest(String loginName,Model model){
        curUserName=loginName;
        List<Tz> tzs=tzServiceImpl.loadAll();
        model.addAttribute("tzss",tzs);
        return "invest";
    }


    /**
     * 根据tz信息id和用户的id给对用的用户添加关注信息
     * @param gztzid
     * @return
     */
    @ResponseBody
    @RequestMapping("/gztz")
    public Boolean showGztz(String gztzid){
        int userId=sysUserServiceImpl.getUserIdByLoginName(curUserName);
        Gz_Tz gz_tz=new Gz_Tz();
        gz_tz.setUserId(userId);
        gz_tz.setTid(Integer.parseInt(gztzid));
        return tzServiceImpl.addNewGzTz(gz_tz);
    }

    /**
     * 跳转到投资项目管理页面
     * @return
     */
    @RequestMapping("/auth_cert")
    public String showAuth_cert(Model model){
        List<Tz> tzs=tzServiceImpl.loadAll();
        model.addAttribute("tzs",tzs);
        return "auth_cert";
    }
}
