package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IPermissionDao;
import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

//    查询所有权限
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

//    查询权限详情
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    //添加权限
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    //删除权限
    public void delete(String id) {

        permissionDao.delete(id);
    }
}
