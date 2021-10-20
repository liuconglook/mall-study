package com.belean.mall.tiny.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.belean.mall.tiny.config.mybatis.MybatisRedisCache;
import com.belean.mall.tiny.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface EsProductDao extends BaseMapper<EsProduct> {

    List<EsProduct> getAllEsProductList(@Param("id") Long id);

}