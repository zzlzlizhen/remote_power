package com.remote.zkwladmin.modules.dev.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remote.zkwladmin.modules.dev.entity.DeviceEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceDao extends BaseMapper<DeviceEntity> {
}
