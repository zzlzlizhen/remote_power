package com.remote.zkwladmin.modules.dev.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("fun_device")
public class DeviceEntity {
    @TableId(type = IdType.UUID)
    private String deviceId;
    private String deviceCode;
    private String deviceName;
    private String groupId;
    private String projectId;
    /**
     * 设备类型
     * */
    private String deviceType;
    /**
     * 光电池状态0: 光弱； 1：光强 2：正在充电
     * */
    private Integer photocellState;
    /**
     * 蓄电池状态 0：过放； 1：欠压； 2：正常； 3：限压值；4：超压； 5：过温；6 激活
     * */
    private Integer batteryState;
    /**
     * 负载状态0：关； 1：开；2：开路保护； 3：直通； 4：短路保护； 5：过载保护；6：过载警告
     * */
    private Integer loadState;
    /**
     *信号状态
     * */
    private Integer signalState;
    /**
     * 运行状态
     * */
    private Integer runState;
    private String light;
    /**
     * 通讯类型 1：2G 2：Lora； 3：NBLot
     * */
    private Integer communicationType;
    /**
     * 开/关/自动 0：关；1：开：2：自动
     * */
    private Integer onOff;
    /**
     * 经度
     * */
    private String longitude;
    /**
     *latitude
     * */
    private String latitude;
    /**
     * 是否删除  1 已删除 0未删除
     * */
    private Integer isDel;
    private Long createUser;
    private String createName;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    /**
     * 蓄电池余量
     * */
    private String batteryMargin;
    /**
     * 蓄电池电压
     * */
    private Double batteryVoltage;
    /**
     * 充电电压
     * */
    private Double photovoltaicCellVoltage;
    /**
     * 充电电流
     * */
    private Double chargingCurrent;
    /**
     * 充电功率
     * */
    private Double chargingPower;
    /**
     * 负载电压
     * */
    private Double loadVoltage;
    /**
     * 负载功率
     * */
    private Double loadPower;
    /**
     *负载电流
     * */
    private Double loadCurrent;
    /**
     * 亮灯时长
     * */
    private String lightingDuration;
    /**
     * 晨亮时长
     * */
    private String morningHours;
    /**
     * 地区
     * */
    private String cityName;
    /**
     * 总放电量
     * */
    private Double dischargeCapacitySum;
    /**
     * 总充电量
     * */
    private Double chargingCapacitySum;
    private Integer version;
    private Long deviceVersion;
}
