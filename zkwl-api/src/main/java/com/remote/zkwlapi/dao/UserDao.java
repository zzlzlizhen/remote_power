

package com.remote.zkwlapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remote.zkwlapi.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户

 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
