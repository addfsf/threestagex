package com.qf.controller;

import com.qf.pojo.SysRole;
import com.qf.pojo.User_Role;
import com.qf.service.SysRoleService;
import com.sun.jdi.IntegerValue;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleServiceImpl;

    /**
     * 加载到角色管理页面
     * @param model
     * @return
     */
    //@RequiresPermissions(value = {"user_edit"})
    @RequestMapping("/role")
    public String showAllRoles(Model model){
        List<SysRole> roles=sysRoleServiceImpl.loadAllRole();
        model.addAttribute("roles",roles);
        return "role";
    }

    private int curentId=0;
    /**
     * 根据用户id加载其对应的角色信息
     * @param userId
     * @return
     */
    @RequestMapping("/assignRole")
    public String showAssignRole(int userId,Model model){
        curentId=userId;
        List<SysRole> unContainRoles=new ArrayList<>();
        List<SysRole> all=sysRoleServiceImpl.loadAllRole();
        List<SysRole> containRoles=sysRoleServiceImpl.loadRoleByUserId(userId);
        for (SysRole role:all){
            for (SysRole role1:containRoles){
                if (!role.equals(role1)){
                    unContainRoles.add(role);
                }
            }
        }
        model.addAttribute("containRoles",containRoles);
        model.addAttribute("unContainRoles",unContainRoles);
        return "assignRole";
    }

    /**
     * 将新的角色添加给关系表中
     * @param opss
     * @return
     */
    @ResponseBody
    @RequestMapping("/roleDistributeAdd")
    public Boolean showDistributeResult(String opss){
        //当前想要增加的角色
        String[] opArray=opss.split("_");
        //定义一个关系类对象接收要存储的信息
        User_Role user_role=null;
        //定义一个参数判断存储是否成功
        int s=0;
        //将新的角色分配信息存储到关系表中
        //String uu="";
        for (int i=0;i<opArray.length;i++){
            user_role=new User_Role();
               // uu +=( "(" + curentId + "," + sysRoleServiceImpl.getRoleIdByRoleName(opArray[i]) + "),");
            user_role.setUserId(curentId);
            user_role.setRoleId(sysRoleServiceImpl.getRoleIdByRoleName(opArray[i]));
            boolean b=sysRoleServiceImpl.addDistribute(user_role);
            if (b){
                s++;
            }
        }
        //uu = uu.substring(0,uu.length()-1);
        //boolean b=sysRoleServiceImpl.addDistributes(uu);
        /*if (b){
          s++;
        }*/
        return s>0? true:false;
    }


    /**
     * 将新的角色用户关系添加到关系表中吗，删除对应的关系信息
     * @param opss
     * @return 修改结果
     */
    @ResponseBody
    @RequestMapping("/roleDistributeReduce")
    public boolean showDistributeReduce(String opss){
//当前想要增加的角色
        String[] opArray=opss.split("_");
        //定义一个关系类对象接收要存储的信息
        User_Role user_role=null;
        //定义一个参数判断存储是否成功
        int s=0;
        //将新的角色分配信息存储到关系表中
        for (int i=0;i<opArray.length;i++){
            user_role=new User_Role();
            user_role.setUserId(curentId);
            user_role.setRoleId(sysRoleServiceImpl.getRoleIdByRoleName(opArray[i]));
            boolean b=sysRoleServiceImpl.delDistribute(user_role);
            if (b){
                s++;
            }
        }
        return s>0? true:false;
    }
}
