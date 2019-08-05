package com.remote.zkwladmin.modules.dev.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remote.zkwladmin.modules.dev.entity.DeviceEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface DeviceDao extends BaseMapper<DeviceEntity> {
    List<DeviceEntity> queryPageList(Map<String,Object> parmas);
}
