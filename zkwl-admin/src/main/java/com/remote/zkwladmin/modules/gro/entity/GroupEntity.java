package com.remote.zkwladmin.modules.gro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("fun_group")
public class GroupEntity {
    @TableId(type = IdType.UUID)
    private String groupId;
    private String projectId;
    private String groupName;
    private String deviceIds;
    private Long createUser;
    private String createName;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    private Integer isDel;
}
