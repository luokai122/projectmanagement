package cn.toprs.projectmanagenment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

/**
 *
 * @author yangyibo
 * @date 17/1/17
 */

public class SysUser {



    public interface Add{}

    public interface Update{}

    public interface Delete{}

    @Null(groups = Add.class,message = "id编号必须为空！")
    private Integer id;

    @NotEmpty(groups = Add.class,message = "用户名不能为空！")
    private String username;

    @NotEmpty(groups = Add.class ,message = "密码不能为空！")
    @NotEmpty(groups = Update.class ,message = "密码不能为空！")
    private String password;

    @NotEmpty(groups = Add.class ,message = "姓名不能为空！")
    @NotEmpty(groups = Update.class ,message = "姓名不能为空！")
    private String nickname;

    @NotEmpty(groups = Add.class ,message = "电话不能为空！")
    @NotEmpty(groups = Update.class ,message = "电话不能为空！")
    private String usertell;

    @NotNull(groups = Add.class,message = "管理区域不能为空！")
    @NotNull(groups = Update.class,message = "管理区域不能为空！")
    private Integer userarea;

    @NotNull(groups = Delete.class,message = "删除标识不能为空！")
    private Integer delFlag;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date creatime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date delDate;

    private List<SysRole> roles;

    private String postive;

    private String adminFlag;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsertell() {
        return usertell;
    }

    public void setUsertell(String usertell) {
        this.usertell = usertell;
    }

    public Integer getUserarea() {
        return userarea;
    }

    public void setUserarea(Integer userarea) {
        this.userarea = userarea;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }


    public String getPostive() {
        return postive;
    }

    public void setPostive(String postive) {
        this.postive = postive;
    }

    public String getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(String adminFlag) {
        this.adminFlag = adminFlag;
    }
}
