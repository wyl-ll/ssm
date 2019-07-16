package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IOrdersDao;
import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    public List<Orders> findAll(int page,int size) {

        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    public Orders findById(String ordersId) {
        return ordersDao.findById(ordersId);
    }
}
