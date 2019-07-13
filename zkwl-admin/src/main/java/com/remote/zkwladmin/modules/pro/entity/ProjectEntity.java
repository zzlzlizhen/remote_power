package com.remote.zkwladmin.modules.pro.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("fun_project")
public class ProjectEntity implements Serializable{
    /*
    * 项目id
    */
    @TableId
    private String projectId;
    /*
    * 项目编号
    */
    private String projectCode;
    /*
    * 项目名称
    */
    private String projectName;
    /*
    * 项目描述
    */
    private String projectDesc;
    /*
    * 所属城市
    */
    private String cityId;
    /*
    * 管理者id
    */
    private Long exclusive_user;
    /*
    * 运行状态
    */
    private Integer runStatus;
    /*
   * 总装机数量
   */
    private Integer sunCount;
    /*
   * 网管数量
   */
    private Integer gatewayCount;
    /*
   * 故障数量
   */
    private Integer faultCount;
    /*
   *报警数量
   */
    private Integer callPoliceCount;
    /*
   * 1 已删除  0未删除
   */
    private Integer isDel;
    /*
   * 创建人id
   */
    private Long createUser;
    /*
   *创建人姓名
   */
    private String createName;
    /*
   * 创建时间
   */
    private Date createTime;
    /*
   * 更新人id
   */
    private Long updateUser;
    /*
   * 更新时间
   */
    private Date updateTime;
    /*
   * 项目状态 1 启动 2停用
   */
    private Integer projectStatus;
}
