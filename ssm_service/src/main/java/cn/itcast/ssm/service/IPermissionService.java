package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll();

    Permission findById(String id);

    void save(Permission permission);

    void delete(String id);
}
