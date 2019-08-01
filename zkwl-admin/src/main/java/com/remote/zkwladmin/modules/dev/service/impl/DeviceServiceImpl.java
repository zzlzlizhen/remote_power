package com.remote.zkwladmin.modules.dev.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remote.zkwladmin.modules.dev.dao.DeviceDao;
import com.remote.zkwladmin.modules.dev.entity.DeviceEntity;
import com.remote.zkwladmin.modules.dev.service.DeviceService;
import org.springframework.stereotype.Service;

@Service("deviceService")
public class DeviceServiceImpl extends ServiceImpl<DeviceDao, DeviceEntity> implements DeviceService{
}