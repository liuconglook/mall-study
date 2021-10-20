package com.belean.mall.tiny.test.mybatis;

import com.belean.mall.tiny.Application;
import com.belean.mall.tiny.common.utils.RedisUtils;
import com.belean.mall.tiny.config.mybatis.MybatisRedisCache;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by belean on 2021/7/25.
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles({"test"})
@Ignore
public class MybatisRedisCacheTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testGetRedisUtils() {
        Assert.assertNotNull(redisUtils);
    }

    @Test
    public void testGetMybatisRedisCache() {
        MybatisRedisCache mybatisRedisCache = new MybatisRedisCache("com.belean.mall.tiny.mbg.mapper.PmsBrandMapper");
        Assert.assertNotNull(mybatisRedisCache);
        mybatisRedisCache.putObject("a", "aaa");
        String a = (String) mybatisRedisCache.getObject("a");
        Assert.assertEquals("aaa", a);
    }
}
