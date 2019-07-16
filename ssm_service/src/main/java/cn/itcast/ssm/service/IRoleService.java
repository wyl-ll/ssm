package cn.itcast.ssm.service;


import cn.itcast.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll();

    Role findByRoleId(String roleId);

    void save(Role role);

    Role findRoleByIdAndAllPermission(String id);

    void addPermissionToRole(String roleId, String[] ids);

    void delete(String id);
}
