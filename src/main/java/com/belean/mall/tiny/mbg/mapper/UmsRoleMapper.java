package com.belean.mall.tiny.mbg.mapper;

import com.belean.mall.tiny.mbg.model.UmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.belean.mall.tiny.config.mybatis.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @Entity com.belean.mall.tiny.mbg.model.UmsRole
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface UmsRoleMapper extends BaseMapper<UmsRole> {
}




