package com.qf.controller;

import com.qf.pojo.Apply;
import com.qf.service.ApplyService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

@Controller
public class ApplyController{
    @Autowired
    private ApplyService applyServiceImpl;

    /**
     * 定义一个string值接收当前要进行实名认证的用户的登录名
     */
    private String curRzUser="x";
    /**
     * 转到开始实名认证的桌面
     * @param loginName 登录用户名
     * @return
     */
    @RequestMapping("/accttype")
    public String showAccttype(String loginName){
        curRzUser=loginName;
        return "accttype";
    }

    /**
     * 添加一个实名认证对象，用来接收新的实名认证信息
     */
    private Apply apply=null;
    /**
     * 实名认证的第一个页面
     * @return
     */
    @RequestMapping("/apply_1")
    public String showApply_1(){
        apply=applyServiceImpl.loadByLoginName(curRzUser);
        return "apply_1";
    }

    /**
     * 跳转到实名认证的第二个页面
     * @return
     */
    @RequestMapping(value = "/apply_2",method = RequestMethod.POST)
    public String showApply_2(String realName,String number,String adress){
        apply.setRealName(realName);
        apply.setNumber(number);
        apply.setAdress(adress);
        return "apply_2";
    }

    /**
     * 展示照片上传的结果
     * @param page 照片信息
     * @return 上传的结果
     */
    @ResponseBody
    @RequestMapping("/apply_2sc")
    public Boolean showApply_2sc(String page){
        if (page!=null) {
            apply.setPage(page);
            return true;
        }
        return false;
    }




    /**
     * 跳转到实名认证的第三个页面，邮箱认证页面
     * @return
     */
    @RequestMapping("/apply_3")
    public String showApply_3(){
        return "apply_3";
    }

    //定义一个随机数生成的类
    private Random random=new Random();
    //定义一个int值接收生成的单个随机数
    private int wsj=0;
    //定义一个String接收验证码
    private String s;
    /**
     * 获取邮箱验证，向邮箱发送验证码
     * @param email 发送验证码的邮箱
     * @return 发送是否成功
     */
    @ResponseBody
    @RequestMapping("/apply_33")
    public Boolean showApply_33(String email){
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
        if (s!=null){
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
        return true;
        }
        return false;
    }


    /**
     * 展示确认验证码页面
     * @return
     */
    @RequestMapping("/apply_4")
    public String showApply_4(){
        return "apply_4";
    }

    /**
     * 展示确认验证码页面
     * @return
     */
    @ResponseBody
    @RequestMapping("/yz")
    public Boolean showApply_4(String emailNr){
        if (emailNr.equals(s)&&applyServiceImpl.upApplyByLoginName(apply)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 管理员页面的实名认证页面
     * @return
     */
    @RequestMapping("/auth_project")
    public String showAuth_project(Model model){
        List<Apply> applies=applyServiceImpl.loadAllApply();
        model.addAttribute("applies",applies);
        return "auth_project";
    }


    /**
     * 根据实名认证信息的id修改实名认证的状态
     * @param id 实名认证信息的id
     * @return 实名认证的结果
     */
    @ResponseBody
    @RequestMapping("/rz")
    public Boolean changeRz(int id){
        return applyServiceImpl.changeState(id);
    }
}
