package com.remote.zkwladmin.modules.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remote.zkwladmin.common.annotation.DataFilter;
import com.remote.zkwladmin.common.utils.Constant;
import com.remote.zkwladmin.modules.dev.dao.DeviceDao;
import com.remote.zkwladmin.modules.dev.entity.DeviceEntity;
import com.remote.zkwladmin.modules.dev.service.DeviceService;
import com.remote.zkwlcommon.utils.PageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("deviceService")
public class DeviceServiceImpl extends ServiceImpl<DeviceDao, DeviceEntity> implements DeviceService{
    @Autowired
    DeviceDao deviceDao;
    @Override
    @DataFilter(subDept = false, user = true)
    public PageUtils queryPage(Map<String, Object> params) {
        String projectId = (String) params.get("projectId");
        String deviceCode = (String) params.get("deviceCode");
        String deviceName = (String) params.get("deviceName");
        String groupId = (String) params.get("groupId");
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
        params.put("deviceCode",deviceCode);
        params.put("deviceName",deviceName);
        params.put("groupId",groupId);
        if(StringUtils.isNotBlank(projectId)){
            params.put("projectId",projectId);
        }else{
            params.put("projectId","0");
        }

        List<DeviceEntity > list = this.deviceDao.queryPageList(params);
        Integer totalCount = 0;
        if(CollectionUtils.isNotEmpty(list) || list.size()>0){
            totalCount = list.size();
        }
        return new PageUtils(list,totalCount, limit, page);
    }

    @Override
    public int add(DeviceEntity deviceEntity) {
        return this.baseMapper.insert(deviceEntity);
    }

    @Override
    public int update(DeviceEntity deviceEntity) {
        if(StringUtils.isBlank(deviceEntity.getDeviceId())||"".equals(deviceEntity.getDeviceId())){
            return 0;
        }
        return this.baseMapper.update(deviceEntity,new QueryWrapper<DeviceEntity>().eq("is_del",0).eq("device_id",deviceEntity.getDeviceId()));
    }

    @Override
    public int delete(String deviceId) {
        return this.baseMapper.deleteById(deviceId);
    }

    @Override
    public DeviceEntity queryInfo(String deviceId) {
        return this.baseMapper.selectOne(new QueryWrapper<DeviceEntity>().eq("device_id",deviceId));
    }
}