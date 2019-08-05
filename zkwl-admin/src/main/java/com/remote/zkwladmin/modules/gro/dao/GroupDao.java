package com.remote.zkwladmin.modules.gro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remote.zkwladmin.modules.gro.entity.GroupEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface GroupDao extends BaseMapper<GroupEntity> {
    List<GroupEntity> queryPageList(Map<String, Object> parmas);
}
