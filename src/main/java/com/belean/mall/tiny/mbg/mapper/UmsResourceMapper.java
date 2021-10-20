package com.belean.mall.tiny.mbg.mapper;

import com.belean.mall.tiny.mbg.model.UmsResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.belean.mall.tiny.config.mybatis.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @Entity com.belean.mall.tiny.mbg.model.UmsResource
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface UmsResourceMapper extends BaseMapper<UmsResource> {
}




