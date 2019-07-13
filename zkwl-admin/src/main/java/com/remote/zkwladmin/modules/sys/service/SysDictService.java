package com.remote.zkwladmin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remote.zkwlcommon.utils.PageUtils;
import com.remote.zkwladmin.modules.sys.entity.SysDictEntity;

import java.util.Map;

/**
 * 数据字典
 *
 *
 */
public interface SysDictService extends IService<SysDictEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

