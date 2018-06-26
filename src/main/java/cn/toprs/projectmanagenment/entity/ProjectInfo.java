package cn.toprs.projectmanagenment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author StrangeLuo
 */
public class ProjectInfo {

    public interface Add{}

    public interface Update{}

    public interface Delete{}

    @Null(groups = Add.class,message = "新增时id必须为空！")
    @NotNull(groups = Update.class,message = "修改时id不能为空！")
    private Integer id;

    @NotEmpty(groups = Add.class,message = "项目编号不能为空！")
    private String  projectid;

    private String department;

    private String investor;

    private String struct;

    @NotEmpty(groups = Add.class,message = "项目名称不能为空！")
    @NotEmpty(groups = Update.class,message = "项目名称不能为空！")
    private String projectname;

    @NotEmpty(groups = Add.class,message = "项目状态不能为空！")
    @NotEmpty(groups = Update.class,message = "项目状态不能为空！")
    private String projectstatus;

    @NotEmpty(groups = Add.class,message = "项目内容不能为空！")
    @NotEmpty(groups = Update.class,message = "项目内容不能为空！")
    private String projectinfo;

    @Pattern(groups = Add.class,regexp = "[0-9]\\d*",message = "请在总投资输入数字！")
    @Pattern(groups = Update.class,regexp = "[0-9]\\d*",message = "请在总投资输入数字！")
    private String amountinvested;

    private Integer belongarea;

    @NotEmpty(groups = Add.class,message = "地址不能为空！")
    @NotEmpty(groups = Update.class,message = "地址不能为空！")
    private String address;

    private Double latitude;

    private Double longitude;

    private String areaid;

    private String area;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date signtime;

    @Pattern(groups = Add.class,regexp = "^$|[0-9]\\d*",message = "请在预期利税输入数字！")
    @Pattern(groups = Update.class,regexp = "^$|[0-9]\\d*",message = "请在预期利税输入数字！")
    private String expecttax;

    @Pattern(groups = Add.class,regexp = "^$|[0-9]\\d*",message = "请在预期产值输入数字！")
    @Pattern(groups = Update.class,regexp = "^$|[0-9]\\d*",message = "请在预期产值输入数字！")
    private String expectvalue;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date starttime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endtime;

    @NotEmpty(groups = Add.class,message = "责任人不能为空！")
    private String responsibleren;

    @NotEmpty(groups = Add.class,message = "联络人不能为空！")
    private String contactren;

    private Date createdate;

    private String  updateuser;

    private Date updatedate;

    private Date deldate;

    private Integer delflag;

    private Integer userarea;

    private String username;

    private String selectStr;

    private String contactrenTell;

    private String responsiblerenTell;

    private String contactrenName;

    private String responsiblerenName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor == null ? null : investor.trim();
    }

    public String getStruct() {
        return struct;
    }

    public void setStruct(String struct) {
        this.struct = struct == null ? null : struct.trim();
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getProjectstatus() {
        return projectstatus;
    }

    public void setProjectstatus(String projectstatus) {
        this.projectstatus = projectstatus == null ? null : projectstatus.trim();
    }

    public String getProjectinfo() {
        return projectinfo;
    }

    public void setProjectinfo(String projectinfo) {
        this.projectinfo = projectinfo == null ? null : projectinfo.trim();
    }



    public Integer getBelongarea() {
        return belongarea;
    }

    public void setBelongarea(Integer belongarea) {
        this.belongarea = belongarea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }





    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getResponsibleren() {
        return responsibleren;
    }

    public void setResponsibleren(String responsibleren) {
        this.responsibleren = responsibleren == null ? null : responsibleren.trim();
    }

    public String getContactren() {
        return contactren;
    }

    public void setContactren(String contactren) {
        this.contactren = contactren == null ? null : contactren.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String  getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
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

    public Integer getUserarea() {
        return userarea;
    }

    public void setUserarea(Integer userarea) {
        this.userarea = userarea;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSelectStr() {
        return selectStr;
    }

    public void setSelectStr(String selectStr) {
        this.selectStr = selectStr;
    }
    public String getContactrenTell() {
        return contactrenTell;
    }

    public void setContactrenTell(String contactrenTell) {
        this.contactrenTell = contactrenTell;
    }

    public String  getResponsiblerenTell() {
        return responsiblerenTell;
    }

    public void setResponsiblerenTell(String responsiblerenTell) {
        this.responsiblerenTell = responsiblerenTell;
    }

    public String getContactrenName() {
        return contactrenName;
    }

    public void setContactrenName(String contactrenName) {
        this.contactrenName = contactrenName;
    }

    public String getResponsiblerenName() {
        return responsiblerenName;
    }

    public void setResponsiblerenName(String responsiblerenName) {
        this.responsiblerenName = responsiblerenName;
    }

    public String getAmountinvested() {
        return amountinvested;
    }

    public void setAmountinvested(String amountinvested) {
        this.amountinvested = amountinvested;
    }

    public String getExpecttax() {
        return expecttax;
    }

    public void setExpecttax(String expecttax) {
        this.expecttax = expecttax;
    }

    public String getExpectvalue() {
        return expectvalue;
    }

    public void setExpectvalue(String expectvalue) {
        this.expectvalue = expectvalue;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }
}