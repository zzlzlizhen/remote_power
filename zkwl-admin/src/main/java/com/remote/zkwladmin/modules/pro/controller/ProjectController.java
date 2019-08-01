package com.remote.zkwladmin.modules.pro.controller;

import com.remote.zkwladmin.common.utils.UUIDUtil;
import com.remote.zkwladmin.modules.pro.entity.ProjectEntity;
import com.remote.zkwladmin.modules.pro.service.ProjectService;
import com.remote.zkwladmin.modules.sys.controller.AbstractController;
import com.remote.zkwlcommon.utils.PageUtils;
import com.remote.zkwlcommon.utils.R;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pro")
public class ProjectController extends AbstractController{
    @Autowired
    private ProjectService projectService;
    //项目分页列表
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @RequiresPermissions("pro:list")
    public R queryList(@RequestParam Map<String,Object> params){
        PageUtils page = projectService.queryPage(params);
        return R.ok().put("page",page);
    }
    //添加项目
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @RequiresPermissions("pro:save")
    public R add(ProjectEntity projectEntity){
        if(StringUtils.isBlank(projectEntity.getProjectId()) || projectEntity.getProjectId().equals("")){
            projectEntity.setProjectId(UUID.randomUUID().toString().replace("-", ""));
        }
        projectEntity.setCreateUser(getUserId());
        projectEntity.setCreateTime(new Date());
        int falg = projectService.add(projectEntity);
        if(falg <= 0){
            return R.error("添加项目失败");
        }
        return R.ok();
    }
    //修改项目
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @RequiresPermissions("pro:update")
    public R update(ProjectEntity projectEntity){
        projectEntity.setUpdateTime(new Date());
        projectEntity.setUpdateUser(getUserId());
        boolean falg = projectService.update(projectEntity);
        if(!falg){
            return R.error("修改项目失败");
        }
        return R.ok();
    }
    //删除改项目
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @RequiresPermissions("pro:delete")
    public R delete(@PathVariable String projectId){
        boolean falg = projectService.delete(projectId);
        if(!falg){
            return R.error("项目删除失败");
        }
        return R.ok();
    }
}
