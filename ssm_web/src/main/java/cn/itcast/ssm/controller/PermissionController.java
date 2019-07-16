package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

//    查询所有权限
    @RequestMapping("/findAll")
    public String findAll(Model model){

        List<Permission> permissionList = permissionService.findAll();

        model.addAttribute("permissionList",permissionList);

        return "permission-list";
    }
//    查询权限详情
    @RequestMapping("/findById")
    public String findById(Model model,String id){

        Permission permission = permissionService.findById(id);

        model.addAttribute("permission",permission);

        return "permission-show";
    }

    //    添加权限
    @RequestMapping("/save")
    public String save(Permission permission){

        permissionService.save(permission);

        return "redirect:findAll";
    }

    //删除权限
    @RequestMapping("/deletePermission")
    public  String delete(String id){

        permissionService.delete(id);

        return "redirect:findAll";
    }
}
