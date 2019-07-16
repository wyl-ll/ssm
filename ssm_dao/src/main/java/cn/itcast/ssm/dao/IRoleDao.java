package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

//    通过用户id查询角色(1到多个)
    @Select("select r.* from role r,users_role ur where r.id=ur.roleId and ur.userId=#{userId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class, many = @Many(select = "cn.itcast.ssm.dao.IPermissionDao.findByRoleId"))
    })
    public List<Role> findById(String userId);

//    查询所有角色
    @Select("select * from role")
    public List<Role> findAll();

//    角色详情
    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "permissions", column = "id", javaType = List.class, many = @Many(select = "cn.itcast.ssm.dao.IPermissionDao.findByRoleId"))
    })
    public Role findByRoleId(String roleId);

    //添加角色
    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    public void save(Role role);


    //    通过用户id查询没有的角色(1到多个)
    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc")
    })
    public List<Role> findByNotId(String userId);

    //为角色添加权限(1)
    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "permissions", column = "id", javaType = List.class, many = @Many(select = "cn.itcast.ssm.dao.IPermissionDao.findByNotRoleId"))
    })
    public Role findRoleByIdAndAllPermission(String id);

    //为角色添加权限(2)
    @Insert("insert into role_permission values(#{id},#{roleId})")
    public void addPermissionToRole(@Param("roleId") String roleId, @Param("id") String id);


    //删除角色
    @Delete("delete from role where id=#{id}")
    void delete(String id);
}
