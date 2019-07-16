package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{id})")
    public List<Traveller> findById(String id);

}
