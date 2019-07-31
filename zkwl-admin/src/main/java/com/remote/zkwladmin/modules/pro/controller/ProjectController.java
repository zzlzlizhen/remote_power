package com.remote.zkwladmin.modules.pro.controller;

import com.remote.zkwladmin.modules.pro.entity.ProjectEntity;
import com.remote.zkwlcommon.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pro")
public class ProjectController {
    //项目分页列表
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public R queryList(){
        return R.ok();
    }
    //添加项目
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public R add(ProjectEntity projectEntity){
        return R.ok();
    }
    //修改项目
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public R update(String projectId){
        return R.ok();
    }
    //修改项目
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public R delete(String projectId){
        return R.ok();
    }
}
