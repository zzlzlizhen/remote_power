package com.remote.zkwladmin.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.remote.zkwladmin.modules.oss.entity.SysOssEntity;
import com.remote.zkwlcommon.utils.PageUtils;

import java.util.Map;

/**
 * 文件上传
 *
 *
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
