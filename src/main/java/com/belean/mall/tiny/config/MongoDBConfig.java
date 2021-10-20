package com.belean.mall.tiny.config;

import com.belean.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
public class MongoDBConfig {
    final MongoTemplate mongoTemplate;
    public MongoDBConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initIndicesAfterStartup() {
        mongoTemplate.indexOps(MemberReadHistory.class)
                .ensureIndex(new Index().on("memberId", Sort.Direction.ASC));
        mongoTemplate.indexOps(MemberReadHistory.class)
                .ensureIndex(new Index().on("productId", Sort.Direction.ASC));
        
    }
}