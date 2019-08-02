package com.remote.zkwladmin.modules.pro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remote.zkwladmin.common.utils.Constant;
import com.remote.zkwladmin.common.utils.Query;
import com.remote.zkwladmin.modules.pro.dao.ProjectDao;
import com.remote.zkwladmin.modules.pro.entity.ProjectEntity;
import com.remote.zkwladmin.modules.pro.service.ProjectService;
import com.remote.zkwlcommon.utils.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 项目
 */
@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectEntity> implements ProjectService{
    @Autowired
    private ProjectDao projectDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String projectCode = (String)params.get("projectCode");
        String projectName = (String)params.get("projectName");
        String exclusiveUser = (String)params.get("exclusiveUser");
        IPage<ProjectEntity> page = this.page(
                new Query<ProjectEntity>().getPage(params),
                new QueryWrapper<ProjectEntity>()
                        .like(StringUtils.isNotBlank(projectCode),"project_code", projectCode)
                        .like(StringUtils.isNotBlank(projectName),"project_name", projectName)
                        .like(StringUtils.isNotBlank(exclusiveUser),"exclusive_user", exclusiveUser).eq("is_del",0)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );
        return new PageUtils(page);
    }

    @Override
    public int add(ProjectEntity projectEntity) {
        return this.baseMapper.insert(projectEntity);
       /* return this.projectDao.add(projectEntity);*/
    }

    @Override
    public int update(ProjectEntity projectEntity) {
        return this.baseMapper.update(projectEntity,new QueryWrapper<ProjectEntity>().eq("is_del",0).eq("project_id",projectEntity.getProjectId()));
    }

    @Override
    public int delete(String projectId) {
        return this.baseMapper.deleteById(projectId);
    }

    @Override
    public ProjectEntity queryInfo(String projectId) {
       return this.baseMapper.selectOne(new QueryWrapper<ProjectEntity>().eq("is_del",0).eq("project_id",projectId));
    }
}
