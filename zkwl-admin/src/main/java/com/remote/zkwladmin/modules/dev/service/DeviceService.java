package com.remote.zkwladmin.modules.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remote.zkwladmin.modules.dev.entity.DeviceEntity;
import com.remote.zkwlcommon.utils.PageUtils;

import java.util.Map;

public interface DeviceService extends IService<DeviceEntity>{
    PageUtils queryPage(Map<String,Object> params);
    int add(DeviceEntity deviceEntity);
    int update(DeviceEntity deviceEntity);
    int delete(String deviceId);
    DeviceEntity queryInfo(String deviceId);
}
