package com.bridgelabz.NoteService.configuration;


import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/******************************************************************************
 *  Compilation:  javac -d bin RedisConfig.java
 *  Execution:    
 *              
 *  
 *  Purpose:       main purpose this class is to configure redis
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   26-11-2019
 *
 ******************************************************************************/

@Configuration
@EnableRedisRepositories
public class RedisConfig {
	@Bean
	   public JedisConnectionFactory jedisConnectionFactory() {
	       RedisProperties properties = redisProperties();
	       RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
	       configuration.setHostName(properties.getHost());
	       configuration.setPort(properties.getPort());

	       return new JedisConnectionFactory(configuration);
	   }

	   @Bean
	   public RedisTemplate<String, Object> redisTemplate() {
	       final RedisTemplate<String, Object> template = new RedisTemplate<>();
	       template.setConnectionFactory(jedisConnectionFactory());
	       template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
	       return template;
	   }

	   @Bean
	   @Primary
	   public RedisProperties redisProperties() {
	       return new RedisProperties();
	   }
}
