package com.remote.zkwladmin.modules.dev.controller;

import com.remote.zkwladmin.modules.dev.entity.DeviceEntity;
import com.remote.zkwladmin.modules.dev.service.DeviceService;
import com.remote.zkwladmin.modules.sys.controller.AbstractController;
import com.remote.zkwlcommon.utils.PageUtils;
import com.remote.zkwlcommon.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController(value = "deviceController")
@RequestMapping(value = "dev")
public class DeviceController extends AbstractController{
    @Autowired
    DeviceService deviceService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions("pro:list")
    public R queryPageList(@RequestParam Map<String,Object> params){
        PageUtils page = deviceService.queryPage(params);
        return R.ok().put("page",page);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @RequiresPermissions("pro:save")
    public R save(DeviceEntity deviceEntity){
        deviceEntity.setDeviceId(UUID.randomUUID().toString().replace("-", ""));
        deviceEntity.setUserId(getUserId());
        deviceEntity.setCreateName(getUser().getUsername());
        deviceEntity.setCreateTime(new Date());
        int n = this.deviceService.add(deviceEntity);
        if(n <= 0){
            return R.error("保存设备数据失败");
        }
        return R.ok();
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @RequiresPermissions("pro:update")
    public R update(DeviceEntity deviceEntity){
        if(StringUtils.isBlank(deviceEntity.getDeviceId())|| "".equals(deviceEntity.getDeviceId())){
            return R.error("设备id不能为空");
        }
        deviceEntity.setIsDel(0);
        deviceEntity.setUpdateUser(getUserId());
        deviceEntity.setUpdateTime(new Date());
        int n = this.deviceService.update(deviceEntity);
        if(n <= 0){
            return R.error("更新设备数据失败");
        }
        return R.ok();
    }
    //删除改项目
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @RequiresPermissions("pro:delete")
    public R delete(@RequestParam("deviceId") String deviceId){
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setDeviceId(deviceId);
        deviceEntity.setIsDel(1);
        int falg = deviceService.update(deviceEntity);
        if(falg <= 0){
            return R.error("设备删除失败");
        }
        return R.ok();
    }
    @RequestMapping(value = "/info/{deviceId}",method = RequestMethod.GET)
    @RequiresPermissions("pro:info")
    public R info(@PathVariable("deviceId") String deviceId){
        DeviceEntity deviceEntity = deviceService.queryInfo(deviceId);
        return R.ok().put("device",deviceEntity);
    }
}
