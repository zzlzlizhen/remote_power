

package com.remote.zkwlapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remote.zkwlapi.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Token

 */
@Mapper
public interface TokenDao extends BaseMapper<TokenEntity> {
	
}
