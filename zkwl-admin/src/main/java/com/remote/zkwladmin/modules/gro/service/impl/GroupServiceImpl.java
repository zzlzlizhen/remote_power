package com.remote.zkwladmin.modules.gro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remote.zkwladmin.common.utils.Constant;
import com.remote.zkwladmin.modules.gro.dao.GroupDao;
import com.remote.zkwladmin.modules.gro.entity.GroupEntity;
import com.remote.zkwladmin.modules.gro.service.GroupService;
import com.remote.zkwlcommon.utils.PageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("groupService")
public class GroupServiceImpl extends ServiceImpl<GroupDao, GroupEntity> implements GroupService {
    @Autowired
    GroupDao groupDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        int page =1;
        if(params.get(Constant.PAGE)!=null){
            page = Integer.parseInt((String)params.get(Constant.PAGE));
        }
        int limit=10;
        if(params.get(Constant.LIMIT) != null){
            limit = Integer.parseInt((String)params.get(Constant.LIMIT));
        }
        int offset = (page-1)*limit;
        params.put("offset",offset);
        params.put("limit",limit);
        List<GroupEntity> list = this.groupDao.queryPageList(params);
        Integer totalCount = 0;
        if(CollectionUtils.isNotEmpty(list) || list.size()>0){
            totalCount = list.size();
        }
        return new PageUtils(list,totalCount, limit, page);
    }

    @Override
    public int add(GroupEntity groupEntity) {
        return this.baseMapper.insert(groupEntity);
    }

    @Override
    public int update(GroupEntity groupEntity) {
        if(StringUtils.isBlank(groupEntity.getGroupId())||"".equals(groupEntity.getGroupId())){
            return 0;
        }
        return this.baseMapper.update(groupEntity,new QueryWrapper<GroupEntity>().eq("is_del",0).eq("group_id",groupEntity.getGroupId()));
    }

    @Override
    public int delete(String groupId) {
        return this.baseMapper.deleteById(groupId);
    }

    @Override
    public GroupEntity queryInfo(String groupId) {
        return this.baseMapper.selectOne(new QueryWrapper<GroupEntity>().eq("is_del",0).eq("group_id",groupId));
    }
}