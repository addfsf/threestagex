package com.qf.controller;

import com.qf.pojo.Apply;
import com.qf.pojo.ApplyMoney;
import com.qf.pojo.SysUser;
import com.qf.pojo.Tz;
import com.qf.service.ApplyMoneyService;
import com.qf.service.ApplyService;
import com.qf.service.SysUserService;
import com.qf.service.TzService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserServiceImpl;

    @Autowired
    private ApplyMoneyService applyMoneyServiceImpl;

    @Autowired
    private TzService tzServiceImpl;

    @Autowired
    private ApplyService applyServiceImpl;
    //定义一个String接收当前登录的会员用户
    private String curLoginName="x";
    /**
     * 根据当前登录的用户名、密码
     * 身份信息，获取登录结果
     * @param loginName 登录的用户名
     * @param password 登录密码
     * @return 到相应的页面
     */
    @RequestMapping(value = "/isLoadMain",method=RequestMethod.POST)
    //获取当前用户登录信息进行登录
    public String showLoginResult(@RequestParam("loginName")String loginName,
                                   @RequestParam("password")String password,
                                   @RequestParam("identity")String identity,
                                  @RequestParam(value = "rememberMe",defaultValue = "0")Integer rememberMe
    ){
        curLoginName=loginName;
        try {
            //获取当前操作对象
            Subject subject= SecurityUtils.getSubject();
            //封装当前登录用户的用户名与登录密码信息
            UsernamePasswordToken token=new UsernamePasswordToken(loginName,password);
            if (rememberMe==1){
                token.setRememberMe(true);
            }
            subject.login(token);
            if (subject.isAuthenticated()){
                if (identity.equals("vip")){
                    return "redirect:member";
                }
                    return "redirect:main";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login";
    }

    /**
     * 会员登录页面
     * 普通会员登录时访问的页面
     * @return
     */
    @RequiresPermissions(value = {"user_forbidden"})
    @RequestMapping("/member")
    public String showMember(Model model){
        //用户已投资的项目信息
        List<Tz> tzs=tzServiceImpl.loadTzByLoginName(curLoginName);
        //用户关注的项目
        List<Tz> gz=tzServiceImpl.loadTzByWTZLoginName(curLoginName);
        //加载用户对应的借款信息集合
        List<ApplyMoney> applyMonies=applyMoneyServiceImpl.loadByLoginName(curLoginName);
        //根据id查询到的实名认证的信息
        Apply apply=applyServiceImpl.loadByLoginName(curLoginName);
        model.addAttribute("apply",apply);
        model.addAttribute("jk",applyMonies);
        model.addAttribute("gz",gz);
        model.addAttribute("tzs",tzs);
        model.addAttribute("loginName",curLoginName);
        return "member";
    }


    /**
     * 跳转到我要借款页面
     * @return
     */
    @RequiresPermissions(value = {"user_forbidden"})
    @RequestMapping("/borrow")
    public String showBorrow(Model model){
        model.addAttribute("loginName",curLoginName);
        return "borrow";
    }


    /**
     * 根据新输入的用户信息注册新用户
     * @param loginName 新的登录名
     * @param password 密码
     * @param email 邮箱
     * @return 注册结果
     */
    @ResponseBody
    @RequestMapping("/zc")
    public Boolean showRegResult(String loginName,String password,String email){
        //定义一个状态表示添加的结果集
        boolean b=false;
        SysUser newUser=new SysUser();
        //设置用户id
        newUser.setUserId(sysUserServiceImpl.getMaxId()+1);
        newUser.setLoginName(loginName);
        //对密码加密存储
        String souce=password;
        String salt=loginName;
        int d=1024;
        Md5Hash md5Hash=new Md5Hash(souce,salt,d);
        String newPassword=md5Hash.toHex();
        newUser.setPassword(newPassword);
        //设置状态值
        newUser.setState(1);
        //设置注册时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        newUser.setCreateTime(simpleDateFormat.format(date));
        //设置真实姓名
        newUser.setRealName("会员");
        //设置邮箱
        newUser.setEmail(email);
        b=sysUserServiceImpl.addNewUser(newUser);
        boolean re=sysUserServiceImpl.addNewUserRole((int) newUser.getUserId());
        Apply apply=new Apply();
        apply.setId(applyServiceImpl.getMaxId()+1);
        apply.setLoginName(loginName);
        apply.setState("未实名");
        applyServiceImpl.addNewApply(apply);
        return b==re? true:false;
    }


    //定义一个接收忘记密码的用户名
    private String curUpUserLoginName="";
    //定义一个随机数生成的类
    private Random  random=new Random();
    //定义一个int值接收生成的单个随机数
    private int wsj=0;
    //定义一个String接收验证码
    private String s;
    @RequestMapping("/applyNumber")
    public String showNumber(String loginName, String email, Model model){
        //Subject subject=SecurityUtils.getSubject();
        curUpUserLoginName=loginName;
        s="";
        String result="success";
        //设置邮件发送的类commons-email的核心类
        HtmlEmail htmlEmail=new HtmlEmail();
        //指定发送邮件的主机smtp.163.com是网易
        htmlEmail.setHostName("smtp.163.com");
        //设置发送邮件人的用户名和授权码
        htmlEmail.setAuthentication("f13720745272@163.com","FSF520WY");
        //设置文件编码格式
        htmlEmail.setCharset("UTF-8");
        //生成6位数的随机验证码
        for (int i=0;i<6;i++){
            wsj=random.nextInt(10);
            s+=wsj;
        }

        try {
            //设置发件人的邮箱地址和昵称
            htmlEmail.setFrom("f13720745272@163.com","ff");
            //设置发送邮件的主题
            htmlEmail.setSubject("测试邮件");
            //设置发送邮件的内容
            htmlEmail.setMsg("您的验证码是"+s);
            //设置收件人地址
            htmlEmail.addTo(email);
            //发送
            htmlEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return "apply2";
    }

    /**
     * 判断验证码结果
     * @param number 用户输入的验证码
     * @return 返回验证码比对结果
     */
    @ResponseBody
    @RequestMapping("/yzResult")
    public boolean showyzResult(String number){
        if (number.equals(s)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 返回修改结果
     * @param password 新的密码
     * @return 修改结果
     */
    @ResponseBody
    @RequestMapping("/xiugai")
    public boolean showxiugai(String password){
        boolean data=false;
        String souce=password;
        String salt=curUpUserLoginName;
        int d=1024;
        Md5Hash md5Hash=new Md5Hash(souce,salt,d);
        String uh=md5Hash.toHex();
        //获取当前要修改密码的用户信息
        SysUser upUser=sysUserServiceImpl.loadSysUserByLoginName(curUpUserLoginName);
        //修改密码
        upUser.setPassword(uh);
        data=sysUserServiceImpl.upPasswordByLoginName(upUser);
        return data;
    }

    /**
     *
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequestMapping("/pdLoginName")
    public boolean showpdLoginName(String loginName){
        SysUser cu=sysUserServiceImpl.loadSysUserByLoginName(loginName);
        if (cu==null){
            return false;
        }
        return true;
    }

    /**
     * 用户管理页面，展示所有用户信息,只有管理员权限才能访问
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"user_edit"})
    @RequestMapping("/user")
    public String showAllUser(@RequestParam(required = false,defaultValue = "0") int page
                             ,@RequestParam(required = false,defaultValue = "4") int rows
                             ,Model model){
        int maxPge=sysUserServiceImpl.getMaxPageByRows(rows);
        if(page>maxPge){
            page=0;
        }
        if (page<0){
            page=maxPge;
        }
        List<SysUser> allUser=sysUserServiceImpl.loadByPage(page,rows);
        for (SysUser user:allUser){
            user.setPassword("********************");
        }
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPge);
        model.addAttribute("users",allUser);
        return "user";
    }


    //定义一个参数接收要修改用户的id
    private Integer upSysUerId=0;
    /**
     * 跳转到修改用户信息页面,根据用
     * 户id查询出对应的用户的具体信
     * 息
     * @return
     */
    @RequestMapping("/edit")
    public String showEdit(int aid,Model model){
        upSysUerId=aid;
        SysUser user=sysUserServiceImpl.loadByUserId(aid);
        user.setPassword("*************");
        model.addAttribute("a",user);
        return "edit";
    }

    /**
     * 根据新的id修改用户的信息
     * @param loginName 新的登录名
     * @param password 新的用户密码
     * @param email 新的验证邮箱
     * @return 修改结果
     */
    @ResponseBody
    @RequestMapping("/alter")
    public Boolean alterResult(String loginName,String password,String email){
        boolean b=false;
        SysUser sysUser=sysUserServiceImpl.loadByUserId(upSysUerId);
        sysUser.setLoginName(loginName);
        String souce1=password;
        String salt1=loginName;
        int d1=1024;
        Md5Hash md5Hash=new Md5Hash(souce1,salt1,d1);
        String uh=md5Hash.toHex();
        sysUser.setPassword(uh);
        sysUser.setEmail(email);
        b=sysUserServiceImpl.upSysUserByUserId(sysUser);
        return b;
    }

    /**
     * 将新增的用户信息添加到数据库
     * @param loginName 登录名
     * @param password 密码
     * @param email 验证邮箱
     * @return 添加结果
     */
    @ResponseBody
    @RequestMapping("/addResult")
    public Boolean showAddResult(String loginName,String password,String email){
        //定义一个状态表示添加的结果集
        boolean b=false;
        SysUser newUser=new SysUser();
        //设置用户id
        newUser.setUserId(sysUserServiceImpl.getMaxId()+1);
        newUser.setLoginName(loginName);
        //对密码加密存储
        String souce=password;
        String salt=loginName;
        int d=1024;
        Md5Hash md5Hash=new Md5Hash(souce,salt,d);
        String newPassword=md5Hash.toHex();
        newUser.setPassword(newPassword);
        //设置状态值
        newUser.setState(1);
        //设置注册时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        newUser.setCreateTime(simpleDateFormat.format(date));
        //设置真实姓名
        newUser.setRealName("会员");
        //设置邮箱
        newUser.setEmail(email);
        b=sysUserServiceImpl.addNewUser(newUser);
        boolean re=sysUserServiceImpl.addNewUserRole((int) newUser.getUserId());
        return b==re? true:false;
    }


    /**
     * 跳转到模糊查询结果的页面
     * @param mh
     * @return
     */
    @RequestMapping("/dim")
    public String showMHResult(String mh,Model model){
        List<SysUser> sysUsers=sysUserServiceImpl.mhSeacher(mh);
        for (SysUser user:sysUsers){
            user.setPassword("********************");
        }
        model.addAttribute("users",sysUsers);
        return "mhResult";
    }

    /**
     * 根据用户id删除用户信息
     * @param uid
     * @return 删除结果
     */
    @ResponseBody
    @RequestMapping("/delete")
    public boolean showDeleteResult(int uid){
        boolean b=sysUserServiceImpl.deleteUserRoleByUserId(uid);
        boolean a=sysUserServiceImpl.deleteByUserId(uid);
        return (a&&b)? true:false;
    }


    /**
     * 批量删除的用户结果
     * @return 批量删除的结果
     */
    @ResponseBody
    @RequestMapping("/delMany")
    public Boolean showDelManyResult(String xz){
        boolean a=false;
        boolean b=false;

        String[] ids=xz.split(",");
        for (int i=0;i<ids.length;i++) {
            if (!ids[i].equals("on")) {
                //根据用户id删除对应的用户信息
                a=sysUserServiceImpl.deleteUserRoleByUserId(Integer.parseInt(ids[i]));
                b = sysUserServiceImpl.deleteByUserId(Integer.parseInt(ids[i]));
            }
        }
        return (a&&b)? true:false;
    }
}
