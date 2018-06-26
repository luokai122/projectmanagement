package cn.toprs.projectmanagenment.mapper.security;

import cn.toprs.projectmanagenment.entity.SysUser;

import java.util.List;


public interface UserDao {

    public SysUser findByUserName(String username);

    public SysUser selectByUserName(String username);

    public void insertUser(SysUser user);

    public void deleteUser(SysUser user);

    public void updateUser(SysUser user);

    List<SysUser> getAllUser();

    List<SysUser> selectUser(String selectStr);

}
