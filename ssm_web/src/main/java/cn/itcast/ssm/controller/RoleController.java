package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

//    查询所有角色
    @RequestMapping("/findAll")
    public String findAll(Model model){

        List<Role> roleList = roleService.findAll();

        model.addAttribute("roleList",roleList);

        return "role-list";
    }

//    角色详情
    @RequestMapping("findById")
    public String findByRoleId(Model model,String id){

        Role role = roleService.findByRoleId(id);

        model.addAttribute("role",role);

        return "role-show";
    }

    //添加角色
    @RequestMapping("/save")
    public String save(Model model,Role role){

        roleService.save(role);

        return "redirect:findAll";
    }

    //为角色添加权限(先查询没有的权限)
    @RequestMapping("/findRoleByIdAndAllPermission")
    public String findRoleByIdAndAllPermission(Model model,String id){

       Role role = roleService.findRoleByIdAndAllPermission(id);

       model.addAttribute("role",role);

       return "role-permission-add";
    }

    //为角色添加没有的权限
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId")String roleId,@RequestParam(name = "ids") String[] ids){

        roleService.addPermissionToRole(roleId,ids);

        return "redirect:findAll";
    }


}
