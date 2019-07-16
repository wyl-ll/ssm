package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IUserInfoDao;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoDao.findByUsername(username);

        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo));

        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(UserInfo userInfo) {

        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();

        List<Role> role = userInfo.getRole();

        for (Role role1 : role) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role1.getRoleName()));
        }

        return list;

    }

//    查询所有用户
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

//    添加用户
    public void save(UserInfo userInfo) {

       userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));

        userInfoDao.save(userInfo);
    }

    public UserInfo findById(String id) {
        return userInfoDao.findById(id);
    }

//    用户添加角色(1-查询用户所有角色)
    public UserInfo findUserByIdAndAllRole(String id) {
        return userInfoDao.findUserByIdAndAllRole(id);
    }

    //给用户添加角色
    public void addRoleToUser(String userId, String[] ids) {

        for (String id : ids) {
            userInfoDao.addRoleToUser(userId,id);
        }

    }
}
