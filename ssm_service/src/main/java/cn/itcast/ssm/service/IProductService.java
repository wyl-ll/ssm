package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll();

    void save(Product product);
}
