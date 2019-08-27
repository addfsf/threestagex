package com.qf.controller;

import com.qf.pojo.Role_Permission;
import com.qf.pojo.SysPermission;
import com.qf.pojo.User_Role;
import com.qf.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SysPermissionController {
    @Autowired
    private SysPermissionService sysPermissionServiceImpl;

    /**
     * 跳转到权限管理页面
     * 加载所有权限信息给前台
     * @return 权限集合
     */
    @RequestMapping("/permission")
    public String showAllPermissions(@RequestParam(required = false,defaultValue = "0") int page
                                    ,@RequestParam(required = false,defaultValue = "4") int rows
                                    ,Model model){
        //根据行数获取最大的页数
        int maxPge=sysPermissionServiceImpl.getMaxPage(rows);
        if(page>maxPge){
            page=0;
        }
        if (page<0){
            page=maxPge;
        }
        List<SysPermission> permissions=sysPermissionServiceImpl.loadPermissions(page,rows);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPge);
        model.addAttribute("permissions",permissions);
        return "permission";
    }

    //定义一个值接收当前要修改的角色id
    private int curRoleId=0;
    /**
     * 根据角色id跳转到相应的角色权限列表下
     * @param rid
     * @return
     */
    @RequestMapping("/assignPermission")
    public String showAssignPermission(int rid, Model model){
        curRoleId=rid;
        //根据角色id查询当前角色拥有的所有权限信息
        List<SysPermission> rolePermission=sysPermissionServiceImpl.loadSyspermissionByRid(rid);
        //加载出所有的权限信息
        List<SysPermission> allPermission=sysPermissionServiceImpl.loadAllPermissions();
        List<SysPermission> uncontainPermission=new ArrayList<>();
        int d=0;
        for (SysPermission x:allPermission){
            for (SysPermission p:rolePermission){
                if (p.getId()==x.getId()) {
                    d++;
                }
            }
            if (d==0) {
                uncontainPermission.add(x);
            }
            d=0;
        }
        model.addAttribute("cont",rolePermission);
        model.addAttribute("uncont",uncontainPermission);
        return "assignPermission";
    }

    /**
     * 将新的角色权限关系添加到关系表中
     * @param opss 新的权限信息
     * @return 添加结果
     */
    @ResponseBody
    @RequestMapping("/sysPermissionAdd")
    public Boolean showRolePermission(String opss){
        //当前想要新增的权限
        String[] opArray=opss.split("!");
        //定义一个关系类对象接收要存储的信息
        Role_Permission role_permission =null;
        //定义一个参数判断存储是否成功
        int s=0;
        //将新的角色分配信息存储到关系表中
        for (int i=0;i<opArray.length;i++){
            role_permission=new Role_Permission();
            role_permission.setRoleId(curRoleId);
            role_permission.setPid(sysPermissionServiceImpl.loadIdByPerName(opArray[i]));
            boolean b=sysPermissionServiceImpl.addNewRolePermission(role_permission);
            if (b){
                s++;
            }
        }

        return s==opArray.length ? true:false;
    }

    /**
     * 将角色的权限信息删除
     * @param opss 要删除的权限信息
     * @return 删除的结果
     */
    @ResponseBody
    @RequestMapping("/sysPermissionReduce")
    public Boolean showSysPermissionReduce(String opss){
        //当前想要新增的权限
        String[] opArray=opss.split("!");
        //定义一个关系类对象接收要存储的信息
        Role_Permission role_permission =null;
        //定义一个参数判断存储是否成功
        int s=0;
        //将新的角色分配信息存储到关系表中
        for (int i=0;i<opArray.length;i++){
            role_permission=new Role_Permission();
            role_permission.setRoleId(curRoleId);
            role_permission.setPid(sysPermissionServiceImpl.loadIdByPerName(opArray[i]));
            boolean b=sysPermissionServiceImpl.reduceRolePermission(role_permission);
            if (b){
                s++;
            }
        }

        return s==opArray.length ? true:false;
    }


    /**
     * 判断权限分配是否成功
     * @param pids 重新分配后的权限信息
     * @return 重新分配的结果
     */
    @ResponseBody
    @RequestMapping("/allocationPermissionOk")
    public boolean showChangeResult(String pids){

        return false;
    }
}
