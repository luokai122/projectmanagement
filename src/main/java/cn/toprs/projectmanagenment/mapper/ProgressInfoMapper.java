package cn.toprs.projectmanagenment.mapper;

import cn.toprs.projectmanagenment.entity.ProgressInfo;

import java.util.List;

public interface ProgressInfoMapper {
    int deleteByPrimaryKey(Integer progressid);

    int insert(ProgressInfo record);

    int insertSelective(ProgressInfo record);

    ProgressInfo selectById(Integer id);

    List<ProgressInfo> selectByPrimaryKey(Integer projectid);

    int updateByPrimaryKeySelective(ProgressInfo record);

    int updateByPrimaryKey(ProgressInfo record);

    int deleteProgress(ProgressInfo progressInfo);
}