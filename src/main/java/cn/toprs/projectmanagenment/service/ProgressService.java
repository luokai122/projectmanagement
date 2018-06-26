package cn.toprs.projectmanagenment.service;

import cn.toprs.projectmanagenment.entity.ProgressInfo;
import cn.toprs.projectmanagenment.entity.ProjectInfo;
import cn.toprs.projectmanagenment.mapper.ProgressInfoMapper;
import cn.toprs.projectmanagenment.mapper.ProjectInfoMapper;
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
public class ProgressService {

    @Autowired
    public ProgressInfoMapper progressInfoMapper;
    @Autowired
    public ProjectInfoMapper projectInfoMapper;

    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public Result selectProgress(Integer projectid){
        List<ProgressInfo> progressInfoList = progressInfoMapper.selectByPrimaryKey(projectid);
        return ResultGenerator.genSuccessResult(progressInfoList);
    }
    @Transactional(rollbackFor = Exception.class)
    public Result insertProgress(ProgressInfo progressInfo,String username){
        progressInfo.setCreatedate(new Date());
        progressInfo.setCreateuser(username);

        ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(progressInfo.getProjectid());
        if(projectInfo==null){
            return ResultGenerator.genFailResult("该项目不存在！");
        }
        if(projectInfo.getResponsibleren().equals(username)||projectInfo.getContactren().equals(username)){
            progressInfoMapper.insert(progressInfo);
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult("只有联络人或者责任人才能填写进程！");

    }
    @Transactional(rollbackFor = Exception.class)
    public Result deleteProgress(ProgressInfo progressInfo,String username){
        progressInfo.setDeldate(new Date());
        progressInfo.setDelflag(0);
        progressInfo.setDeluser(username);
        ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(progressInfo.getProjectid());
        if(projectInfo==null){
            return ResultGenerator.genFailResult("该项目不存在！");
        }
        if(projectInfo.getResponsibleren().equals(username)||projectInfo.getContactren().equals(username)){
            progressInfoMapper.deleteProgress(progressInfo);
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult("只有联络人或者责任人才能删除进程！");
    }



}
