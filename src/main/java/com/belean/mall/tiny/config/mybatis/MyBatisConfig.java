package com.belean.mall.tiny.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.belean.mall.tiny.dao", "com.belean.mall.tiny.mbg.mapper"})
public class MyBatisConfig {
}