package com.qf.controller;

import com.qf.pojo.ApplyMoney;
import com.qf.service.ApplyMoneyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ApplyMoneyController {

    @Autowired
    private ApplyMoneyService applyMoneyServiceImpl;

    /**
     * 将申请贷款的信息存入到数据库中
     * @param loginName 申请人
     * @param moneyx 申请的钱数
     * @param times 贷款的时间长度
     * @param zfbNumber 接收贷款的支付宝账号
     * @param dbFunctiom 担保类型
     * @param jkyt 借款用途
     * @param jkms 借款描述
     * @return 申请结果页面
     */
    @RequestMapping(value = "/applyMoney",method = RequestMethod.POST)
    public String showApplyResult(@RequestParam("loginName")String loginName,
                                  @RequestParam("moneyx")String moneyx,
                                  @RequestParam("times")String times,
                                  @RequestParam("zfbNumber")String zfbNumber,
                                  @RequestParam("dbFunctiom")String dbFunctiom,
                                  @RequestParam("jkyt")String jkyt,
                                  @RequestParam("jkms")String jkms,
                                  Model model){
        System.out.println(dbFunctiom);
        //定义一个状态值接收存储结果的状态
        Boolean b=false;
        //获取最大的当前要存储的信息的id
        int id=applyMoneyServiceImpl.getMaxId()+1;
        //定义一个对象存储要存放的贷款信息
        ApplyMoney applyMoney=new ApplyMoney(
                                        id,
                                        loginName,
                                        moneyx+"万元",
                                        times+"月",
                                        zfbNumber,
                                        dbFunctiom,
                                        jkyt,
                                        jkms,
                                        1);
       b= applyMoneyServiceImpl.addNewApply(applyMoney)>0? true:false;
       model.addAttribute("b",b);
        return "applyMoneyState";
    }

    /**
     * 此处时一对多的关系，所以不用关系表
     * 根据贷款id和用户名删除对应的贷款信息
     * @param id 贷款信息id
     * @param loginName 贷款人姓名
     * @return 撤销结果
     */
    @ResponseBody
    @RequestMapping("/cxappl")
    public Boolean showcxResult(int id,String loginName){
        boolean b=false;
        //封装要撤销的贷款信息
        ApplyMoney applyMoney=new ApplyMoney();
        applyMoney.setId(id);
        applyMoney.setLoginName(loginName);
        //到数据库中删除对应的借款人的借款信息
        b= applyMoneyServiceImpl.delApplByapplUser(applyMoney);
        return b;
    }

    /**
     * 管理员页面，贷款业务审核
     * @param model
     * @return 贷款审核页面
     */
    @RequestMapping("/auth_borrow")
    public String showAuthborrow(@RequestParam(required = false,defaultValue = "0") int page
                                ,@RequestParam(required = false,defaultValue = "4") int rows
                                ,Model model){
        //获取最大页数
        int maxPge=applyMoneyServiceImpl.getMaxPage(rows);
        if(page>maxPge){
            page=0;
        }
        if (page<0){
            page=maxPge;
        }
        //加载所有贷款信息展示
        List<ApplyMoney> applyMonies=applyMoneyServiceImpl.loadAllApplyMoney(page,rows);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPge);
        model.addAttribute("applymoneys",applyMonies);
        return "auth_borrow";
    }

}
