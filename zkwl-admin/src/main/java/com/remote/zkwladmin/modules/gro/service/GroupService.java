package com.remote.zkwladmin.modules.gro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remote.zkwladmin.modules.gro.entity.GroupEntity;
import com.remote.zkwlcommon.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface GroupService extends IService<GroupEntity>{
    PageUtils queryPage(Map<String, Object> params);
    int add(GroupEntity groupEntity);
    int update(GroupEntity groupEntity);
    int delete(String groupId);
    GroupEntity queryInfo(String groupId);
    List<GroupEntity> queryByProjectId(String projectId);
}
