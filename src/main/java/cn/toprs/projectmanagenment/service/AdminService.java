package cn.toprs.projectmanagenment.service;

import cn.toprs.projectmanagenment.entity.SysRole;
import cn.toprs.projectmanagenment.entity.SysUser;
import cn.toprs.projectmanagenment.mapper.security.RoleDao;
import cn.toprs.projectmanagenment.mapper.security.UserDao;
import cn.toprs.projectmanagenment.result.Result;
import cn.toprs.projectmanagenment.result.ResultGenerator;
import cn.toprs.projectmanagenment.util.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author StrangeLuo
 */
@Service
public class AdminService {
    @Autowired
    public UserDao userDao;

    @Autowired
    public RoleDao roleDao;

    public final static String ADMIN_FLAG_TRUE = "1";

    /**
     * 新增User
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Result insertUser(SysUser user){
        user.setPassword(BCryptUtil.bCrypt(user.getPassword()));
        user.setCreatime(new Date());
        SysUser sysUser = userDao.selectByUserName(user.getUsername());
        if(sysUser==null){
            userDao.insertUser(user);
            sysUser = userDao.selectByUserName(user.getUsername());
        }else{
            return ResultGenerator.genFailResult("用户名不能重复！");
        }
        roleDao.insertRole(new SysRole(sysUser.getId(),2));
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Result deleteUser(SysUser user){
        user.setDelDate(new Date());
        userDao.deleteUser(user);
        return ResultGenerator.genSuccessResult();
    }

    @Transactional(rollbackFor = Exception.class)
    public Result updateUser(SysUser user){
        if(user.getPassword().contains("*")){
            user.setPassword(null);
        }else{
            user.setPassword(BCryptUtil.bCrypt(user.getPassword()));
        }
        SysUser sysUser = userDao.selectByUserName(user.getUsername());
        if(sysUser!=null){
            userDao.updateUser(user);
        }else{
            return ResultGenerator.genFailResult("未找到该用户！");
        }

        if(user.getAdminFlag()!=null){
            if(ADMIN_FLAG_TRUE.equals(user.getAdminFlag())){
                roleDao.updateRole(new SysRole(sysUser.getId(),1));
            }else{
                roleDao.updateRole(new SysRole(sysUser.getId(),2));
            }
        }

        return ResultGenerator.genSuccessResult();
    }

    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public Result getAllUser(){
        List<SysUser> sysUsers = userDao.getAllUser();

        return ResultGenerator.genSuccessResult(sysUsers);
    }

    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public Result selectUser(String selectStr){
        List<SysUser> sysUsers = userDao.selectUser(selectStr);

        return ResultGenerator.genSuccessResult(sysUsers);
    }

}
