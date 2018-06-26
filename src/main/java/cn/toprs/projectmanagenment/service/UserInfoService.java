package cn.toprs.projectmanagenment.service;

import cn.toprs.projectmanagenment.entity.SysUser;
import cn.toprs.projectmanagenment.mapper.security.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author StrangeLuo
 */
@Service
public class UserInfoService {

    @Autowired
    public UserDao userDao;

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public SysUser getUserInfo(String username){
       return userDao.selectByUserName(username);
    }

}
