package cn.toprs.projectmanagenment.mapper;

import cn.toprs.projectmanagenment.entity.ProjectInfo;
import cn.toprs.projectmanagenment.entity.SysUser;

import java.util.List;

public interface ProjectInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectInfo record);

    int insertSelective(ProjectInfo record);

    ProjectInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectInfo record);

    int updateByPrimaryKey(ProjectInfo record);

    List<ProjectInfo> selectProject(SysUser user);

    int deleteById(ProjectInfo record);

    List<ProjectInfo> selectProjectByCon(ProjectInfo projectInfo);

    List<ProjectInfo> selectProjectByLike(ProjectInfo projectInfo);

}