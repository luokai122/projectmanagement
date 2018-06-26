package cn.toprs.projectmanagenment.mapper.security;

import cn.toprs.projectmanagenment.entity.SysRole;


public interface RoleDao {
    public void insertRole(SysRole sysRole);

    public void updateRole(SysRole sysRole);
}
