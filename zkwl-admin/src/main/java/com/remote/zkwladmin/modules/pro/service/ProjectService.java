package com.remote.zkwladmin.modules.pro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remote.zkwladmin.modules.pro.entity.ProjectEntity;
import com.remote.zkwlcommon.utils.PageUtils;

import java.util.Map;

public interface ProjectService extends IService<ProjectEntity>{
    /**
     * 分页查询项目
     * */
    PageUtils queryPage(Map<String, Object> params);
    /**
     * 添加项目
     * */
    int add(ProjectEntity projectEntity);
    /**
     * 修改项目
     * */
    int update(ProjectEntity projectEntity);
    /**
     * 删除项目
     * */
    int delete(String projectId);
    ProjectEntity queryInfo(String projectId);
}
