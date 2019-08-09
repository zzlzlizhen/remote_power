package com.remote.zkwladmin.modules.gro.controller;

import com.remote.zkwladmin.modules.gro.entity.GroupEntity;
import com.remote.zkwladmin.modules.gro.service.GroupService;
import com.remote.zkwladmin.modules.sys.controller.AbstractController;
import com.remote.zkwlcommon.utils.PageUtils;
import com.remote.zkwlcommon.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController(value = "groupController")
@RequestMapping(value = "/gro")
public class GroupController extends AbstractController{
    @Autowired
    GroupService groupService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions("pro:list")
    public R queryPageList(@RequestParam Map<String,Object> params){
        PageUtils page = groupService.queryPage(params);
        return R.ok().put("page",page);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @RequiresPermissions("pro:save")
    public R save(GroupEntity groupEntity){
        groupEntity.setGroupId(UUID.randomUUID().toString().replace("-", ""));
        groupEntity.setUserId(getUserId());
        groupEntity.setCreateName(getUser().getUsername());
        groupEntity.setCreateTime(new Date());
        int n = this.groupService.add(groupEntity);
        if(n <= 0){
            return R.error("保存分组数据失败");
        }
        return R.ok();
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @RequiresPermissions("pro:update")
    public R update(GroupEntity groupEntity){
        if(StringUtils.isBlank(groupEntity.getGroupId())|| "".equals(groupEntity.getGroupId())){
            return R.error("分组id不能为空");
        }
        groupEntity.setUpdateUser(getUserId());
        groupEntity.setUpdateTime(new Date());
        int n = this.groupService.update(groupEntity);
        if(n <= 0){
            return R.error("更新分组数据失败");
        }
        return R.ok();
    }
    //删除分组
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @RequiresPermissions("pro:delete")
    public R delete(@RequestParam("groupId") String groupId){
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setIsDel(1);
        groupEntity.setGroupId(groupId);
        int falg = groupService.update(groupEntity);
        if(falg <= 0){
            return R.error("分组管理删除失败");
        }
        return R.ok();
    }
    @RequestMapping(value = "info/{groupId}",method = RequestMethod.GET)
    @RequiresPermissions("pro:info")
    public R info(@PathVariable("groupId") String groupId){
        GroupEntity groupEntity = groupService.queryInfo(groupId);
        return R.ok().put("group",groupEntity);
    }

    @RequestMapping(value = "/groupList/{projectId}",method = RequestMethod.GET)
    @RequiresPermissions("pro:list")
    public R queryGroupList(@PathVariable("projectId")String projectId){
        List<GroupEntity> groupEntityList = groupService.queryByProjectId(projectId);
        return R.ok().put("groupList",groupEntityList);
    }
}
