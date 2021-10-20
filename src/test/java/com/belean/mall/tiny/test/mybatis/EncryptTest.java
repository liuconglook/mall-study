package com.belean.mall.tiny.test.mybatis;

import com.baomidou.mybatisplus.core.toolkit.AES;
import com.belean.mall.tiny.Application;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author belean
 * @date 2021/7/15
 **/
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EncryptTest {

    @Autowired
    private Environment environment;

    @Value("${test.type}")
    private String testType;

    @Test
    @Ignore
    public void testEncrypt() {
        String url = environment.getProperty("spring.datasource.url");
        String userName = environment.getProperty("spring.datasource.username");
        String password = environment.getProperty("spring.datasource.password");
        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        System.out.println(randomKey);
        
        // 随机密钥加密
        String urlEncrypt = AES.encrypt(url, randomKey);
        String userNameEncrypt = AES.encrypt(userName, randomKey);
        String passwordEncrypt = AES.encrypt(password, randomKey);
        System.out.println(urlEncrypt);
        System.out.println(userNameEncrypt);
        System.out.println(passwordEncrypt);
    }

    @Test
    public void test() {
        String url = AES.decrypt("3IhnGN9tTjg6gBUNhSNYO8uIIj9ESlebse0dyxHUcXlRMQacFowlmXgYbe/EACZCAvrxM3Yy2TLoci2Ep661NY/9G1jxdvX3RK5l0bKwNY7vtd46Gd4a6v2lvqXsAp9Z8ez15jcFmyEQ3hI6eqokp23ZNNluTDWpeG1moLDr5LY="
                , "7e7275f1c40b844d");
        System.out.println(url);
    }
    @Test
    public void test2() {
        String str = AES.encrypt("redis", "7e7275f1c40b844d");
        System.out.println(str);
    }
    @Test
    public void test3() {
        String url = AES.decrypt("ZpYhZO3rwR13GSsFmSVXgQ=="
                , "7e7275f1c40b844d");
        System.out.println(url);
    }
    @Test
    public void testType() {
        Assert.assertEquals("unit-test", testType);
    }
}
