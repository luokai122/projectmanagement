package cn.toprs.projectmanagenment.entity;

/**
 * Created by yangyibo on 17/1/17.
 */

public class SysRole {

    private Integer id;
    private String name;
    private Integer sysRoleId;
    private Integer sysUserId;

    public SysRole() {
    }

    public SysRole(Integer sysUserId,Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
        this.sysUserId = sysUserId;
    }

    public SysRole(Integer id, String name, Integer sysRoleId, Integer sysUserId) {
        this.id = id;
        this.name = name;
        this.sysRoleId = sysRoleId;
        this.sysUserId = sysUserId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }
}
