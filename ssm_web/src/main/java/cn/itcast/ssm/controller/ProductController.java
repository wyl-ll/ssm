package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Product> productList = productService.findAll();

       // System.out.println(productList);

        model.addAttribute("productList" ,productList);

        return "product-list";
    }

    @RequestMapping("/save")
    public String save(Product product){

        System.out.println(product);

        productService.save(product);

        return "redirect:findAll";
    }

}
