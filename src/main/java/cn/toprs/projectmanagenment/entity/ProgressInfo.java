package cn.toprs.projectmanagenment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

public class ProgressInfo {

    public MultipartFile[] getImgs() {
        return imgs;
    }

    public void setImgs(MultipartFile[] imgs) {
        this.imgs = imgs;
    }

    public interface Add{}

    public interface Update{}

    public interface Delete{}

    @NotNull(groups = Delete.class,message = "进程编号不能为空！")
    @Null(groups = Add.class,message = "不能添加进程编号！")
    private Integer progressid;

    @NotNull(groups = Add.class,message = "项目编号不能为空！")
    private Integer projectid;

    private String progressinfo;

    private String img;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date createdate;

    private String createuser;

    private String deluser;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date deldate;

    private Integer delflag;

    private MultipartFile[] imgs;

    public Integer getProgressid() {
        return progressid;
    }

    public void setProgressid(Integer progressid) {
        this.progressid = progressid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getProgressinfo() {
        return progressinfo;
    }

    public void setProgressinfo(String progressinfo) {
        this.progressinfo = progressinfo == null ? null : progressinfo.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public String getDeluser() {
        return deluser;
    }

    public void setDeluser(String deluser) {
        this.deluser = deluser == null ? null : deluser.trim();
    }

    public Date getDeldate() {
        return deldate;
    }

    public void setDeldate(Date deldate) {
        this.deldate = deldate;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }
}