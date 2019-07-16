package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IUserInfoDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "role", column = "id", javaType = List.class, many = @Many(select = "cn.itcast.ssm.dao.IRoleDao.findById"))

    })
    public UserInfo findByUsername(String username);

//    查询所有用户
    @Select("select * from users")
    public List<UserInfo> findAll();

//    添加用户
    @Insert("insert into users (username,password,email,phoneNum,status) values (#{username},#{password},#{email},#{phoneNum},#{status})")
    public void save(UserInfo userInfo);


//    用户详情
    @Select("select * from users where id=#{userId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "role", column = "id", javaType = List.class, many = @Many(select = "cn.itcast.ssm.dao.IRoleDao.findById"))

    })
    public UserInfo findById(String userId);

    //    用户添加角色(1-查询用户所有角色)
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "role", column = "id", javaType = List.class, many = @Many(select = "cn.itcast.ssm.dao.IRoleDao.findByNotId"))
    })
    public UserInfo findUserByIdAndAllRole(String id);



    @Insert("insert into users_role values(#{userId},#{id})")
    public void addRoleToUser(@Param("userId") String userId, @Param("id") String id);
}
