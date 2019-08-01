package com.remote.zkwladmin.modules.pro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remote.zkwladmin.modules.pro.entity.ProjectEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProjectDao extends BaseMapper<ProjectEntity>{
    boolean add(@Param("projectEntity") ProjectEntity projectEntity);
    boolean update(@Param("projectEntity") ProjectEntity projectEntity);
    boolean delete(@Param("projectId") String projectId);
}
