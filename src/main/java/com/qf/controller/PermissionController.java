package com.qf.controller;

import com.qf.pojo.Permission;
import com.qf.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionServiceImpl;


    /**
     * 加载所有的权限信息
     * @return 权限集合
     */
 /*   @ResponseBody
    @RequestMapping("/loadAllPermission")
    public List<Permission> showAllPermission(){
        List<Permission> permissions=permissionServiceImpl.loadAllPermission();
        return permissions;
    }*/
}
