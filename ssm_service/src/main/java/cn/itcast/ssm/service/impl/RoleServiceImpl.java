package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IRoleDao;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    //查询所有角色
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    //通过角色id查询角色
    public Role findByRoleId(String roleId) {
        return roleDao.findByRoleId(roleId);
    }

//    添加保存角色
    public void save(Role role) {
        roleDao.save(role);
    }

    //为角色添加权限(1)
    public Role findRoleByIdAndAllPermission(String id) {
        return roleDao.findRoleByIdAndAllPermission(id);
    }
    //为角色添加权限(2)
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String id : ids) {
            roleDao.addPermissionToRole(roleId,id);
        }
    }

    public void delete(String id) {
        roleDao.delete(id);
    }
}
