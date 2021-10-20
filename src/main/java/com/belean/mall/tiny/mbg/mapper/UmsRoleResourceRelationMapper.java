package com.belean.mall.tiny.mbg.mapper;

import com.belean.mall.tiny.mbg.model.UmsRoleResourceRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.belean.mall.tiny.config.mybatis.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @Entity com.belean.mall.tiny.mbg.model.UmsRoleResourceRelation
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface UmsRoleResourceRelationMapper extends BaseMapper<UmsRoleResourceRelation> {
}




