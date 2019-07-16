package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    //通过角色id查询权限
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findByRoleId(String roleId);

    //查询所有权限
    @Select("select * from permission")
    public List<Permission> findAll();

//    查询权限详情
    @Select("select * from permission where id=#{id}")
    public Permission findById(String id);

    //添加权限
    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    public void save(Permission permission);

    //通过角色id查询没有的权限
    @Select("select * from permission where not id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findByNotRoleId(String roleId);

    //删除权限
    @Delete("delete from permission where id=#{id}")
    public void delete(String id);
}
