package com.belean.mall.tiny.mbg.mapper;

import com.belean.mall.tiny.mbg.model.UmsAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.belean.mall.tiny.config.mybatis.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @Entity com.belean.mall.tiny.mbg.model.UmsAdmin
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {
}




