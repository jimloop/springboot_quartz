package com.example.quartz;

import com.example.quartz.util.StandaloneRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"com.example.quartz"})
public class DemoApplication {

    @Autowired
    StandaloneRedisConfig standaloneRedisConfig;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

//    @Autowired
//    private RedisTemplate redisTemplate=null;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @PostConstruct
//    public void init(){
//        initRedisTemplate();
//    }
//
//    private void initRedisTemplate(){
//        RedisSerializer stringSerializer= redisTemplate.getStringSerializer();
//        redisTemplate.setKeySerializer(stringSerializer);
//        redisTemplate.setHashKeySerializer(stringSerializer);
//    }

    @Bean
    @ConditionalOnBean(value = {StandaloneRedisConfig.class})
    public RedisConnectionFactory standalloneRedisConnectionFactory(){
        JedisConnectionFactory factory=new JedisConnectionFactory(
                new RedisStandaloneConfiguration(standaloneRedisConfig.getHost(),standaloneRedisConfig.getPort()));
        factory.setPassword("123456");
        return factory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        return new StringRedisTemplate(redisConnectionFactory);
    }
}
