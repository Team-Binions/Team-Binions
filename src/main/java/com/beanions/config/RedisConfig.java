package com.beanions.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RedisConfig {

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<String, Integer> redisTemplate() {
//        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
////        System.out.println(redisTemplate);
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//
//        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(
//                new ObjectMapper().activateDefaultTyping(
//                        BasicPolymorphicTypeValidator.builder().build(),
//                        ObjectMapper.DefaultTyping.NON_FINAL
//                ).setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
//        );
//
//
//        redisTemplate.setValueSerializer(serializer);
//        redisTemplate.setHashValueSerializer(serializer);
//        return redisTemplate;
//    }
}
