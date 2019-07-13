package com.remote.zkwladmin.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.remote.zkwladmin.modules.sys.entity.SysLogEntity;
import com.remote.zkwlcommon.utils.PageUtils;

import java.util.Map;


/**
 * 系统日志
 *
 *
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
