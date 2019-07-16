package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    //查找所有用户
    @RequestMapping("/findAll")
    public String findAll(Model model) {

        List<UserInfo> userList = userInfoService.findAll();

        model.addAttribute("userList",userList);

        return "user-list";
    }

    //添加用户
    @RequestMapping("/save")
    public String save(Model model, UserInfo userInfo){

        userInfoService.save(userInfo);

        return "redirect:findAll";
    }


//    用户详情
    @RequestMapping("/findById")
    public String findById(Model model,String id){

       UserInfo userInfo = userInfoService.findById(id);

       model.addAttribute("user",userInfo);

        return  "user-show";
    }

    //给用户添加角色(先查询用户的没有角色)
    @RequestMapping("/findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(Model model,String id){

       UserInfo userInfo = userInfoService.findUserByIdAndAllRole(id);

        model.addAttribute("user",userInfo);

        return "user-role-add";
    }

    //给用户添加角色(1到多个)
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name ="userId")String userId, @RequestParam(name ="ids")String[] ids){

       userInfoService.addRoleToUser(userId,ids);

        return "redirect:findAll";
    }

}
