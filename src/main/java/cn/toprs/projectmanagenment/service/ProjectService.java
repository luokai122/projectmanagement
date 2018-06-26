package cn.toprs.projectmanagenment.service;

import cn.toprs.projectmanagenment.entity.ProjectInfo;
import cn.toprs.projectmanagenment.entity.SysUser;
import cn.toprs.projectmanagenment.mapper.ProjectInfoMapper;
import cn.toprs.projectmanagenment.mapper.security.UserDao;
import cn.toprs.projectmanagenment.result.Result;
import cn.toprs.projectmanagenment.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author StrangeLuo
 */
@Service
public class ProjectService {

    @Autowired
    public ProjectInfoMapper projectInfoMapper;
    @Autowired
    public UserDao userDao;

    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public Result selectProject(SysUser userInfo){

        List<ProjectInfo> projects = projectInfoMapper.selectProject(userInfo);

        return ResultGenerator.genSuccessResult(projects);
    }
    @Transactional(rollbackFor = Exception.class)
    public Result insertProject(ProjectInfo projectInfo){
        projectInfo.setCreatedate(new Date());
        projectInfo.setDelflag(1);
        projectInfoMapper.insert(projectInfo);
        return ResultGenerator.genSuccessResult();
    }
    @Transactional(rollbackFor = Exception.class)
    public Result updateProject(ProjectInfo projectInfo,String username){
        projectInfo.setUpdatedate(new Date());
        projectInfo.setUpdateuser(username);

        ProjectInfo projectInfo1 = projectInfoMapper.selectByPrimaryKey(projectInfo.getId());
        if(projectInfo==null){
            return ResultGenerator.genFailResult("该项目不存在！");
        }
        if(projectInfo.getResponsibleren().equals(username)||projectInfo.getContactren().equals(username)){
            projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult("只有该项目联络人或者责任人才能修改项目！");
    }
    @Transactional(rollbackFor = Exception.class)
    public Result deleteProject(Integer id,String username){
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setId(id);
        projectInfo.setDeldate(new Date());
        projectInfo.setDelflag(0);
        ProjectInfo projectInfo1 = projectInfoMapper.selectByPrimaryKey(projectInfo.getId());
        if(projectInfo1==null){
            return ResultGenerator.genFailResult("该项目不存在！");
        }
        if(projectInfo.getResponsibleren().equals(username)||projectInfo.getContactren().equals(username)){
            projectInfoMapper.deleteById(projectInfo);
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult("只有该项目联络人或者责任人才能修改项目！");

    }

    @Transactional(rollbackFor = Exception.class)
    public Result selectProjectByCon(ProjectInfo projectInfo){

        List<ProjectInfo> projects = projectInfoMapper.selectProjectByCon(projectInfo);
        for(ProjectInfo projectInfo1:projects){
            SysUser user = userDao.selectByUserName(projectInfo1.getResponsibleren());
            projectInfo1.setResponsiblerenTell(user.getUsertell());
            projectInfo1.setResponsiblerenName(user.getNickname());
            SysUser user1 = userDao.selectByUserName(projectInfo1.getContactren());
            projectInfo1.setContactrenTell(user1.getUsertell());
            projectInfo1.setContactrenName(user1.getNickname());
        }

        return ResultGenerator.genSuccessResult(projects);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result selectProjectByLike(ProjectInfo projectInfo){

        List<ProjectInfo> projects = projectInfoMapper.selectProjectByLike(projectInfo);

        return ResultGenerator.genSuccessResult(projects);
    }

}
