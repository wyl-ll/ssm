package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductDao {

    @Select("select * from product")
    public List<Product> findAll();

   /* @Insert("insert into product  values(null,#{productNum},#{productName},#{departureTime},#{cityName},#{productPrice},#{productStatus},#{productDesc})")
    void save(Product product);*/

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    @Select("select * from product where id=#{id}")
    public Product findById(String id);

}
