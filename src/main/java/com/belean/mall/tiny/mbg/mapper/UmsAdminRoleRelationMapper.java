package com.belean.mall.tiny.mbg.mapper;

import com.belean.mall.tiny.mbg.model.UmsAdminRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.belean.mall.tiny.config.mybatis.MybatisRedisCache;
import com.belean.mall.tiny.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * @Entity com.belean.mall.tiny.mbg.model.UmsAdminRoleRelation
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface UmsAdminRoleRelationMapper extends BaseMapper<UmsAdminRoleRelation> {
    List<UmsPermission> getPermissionList(Long adminId);
}




