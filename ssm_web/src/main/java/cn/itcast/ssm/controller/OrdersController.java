package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

   /* @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Orders> ordersList = ordersService.findAll();
        System.out.println(ordersList);

        model.addAttribute("ordersList",ordersList );

        return "orders-list";
    }
    */

//    查询所有订单(分页)
    @RequestMapping("/findAll")
    public String findAll(Model model, @RequestParam(name = "page",required = true,defaultValue = "1")int page,@RequestParam(name="size",required = true,defaultValue = "5")int size){

        List<Orders> ordersList = ordersService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(ordersList);

        model.addAttribute("pageInfo",pageInfo );

        return "orders-page-list";
    }



//    订单详情/
    @RequestMapping("/findById")
    public String findById(Model model, @RequestParam(name = "id",required = true)String id){

      Orders orders = ordersService.findById(id);

        model.addAttribute("orders",orders );

        return "orders-show";
    }


}
