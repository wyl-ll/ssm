package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Member;
import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTimeStr", column = "orderTimeStr"),
            @Result(property = "orderStatusStr", column = "orderStatusStr"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select ="cn.itcast.ssm.dao.IProductDao.findById" ))})
    public List<Orders> findAll();




    @Select("select * from orders where id=#{ordersId}")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTimeStr", column = "orderTimeStr"),
            @Result(property = "orderStatusStr", column = "orderStatusStr"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select ="cn.itcast.ssm.dao.IProductDao.findById" )),
            @Result(property = "member", column = "memberId",javaType = Member.class,one = @One(select ="cn.itcast.ssm.dao.IMemberDao.findById" )),
            @Result(property = "travellers", column = "id",javaType = List.class,many = @Many(select ="cn.itcast.ssm.dao.ITravellerDao.findById" )),
    })

    Orders findById(String ordersId);
}
