package com.rlocke.redisSpringApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RedisSpringAppApplication {

	@Bean
	RedisClusterConfiguration clusterConfig() {
		return new RedisClusterConfiguration()
				.clusterNode("192.168.99.100", 7001)
				.clusterNode("192.168.99.100", 7002)
				.clusterNode("192.168.99.100", 7003);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory(clusterConfig());
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setExposeConnection(true);
		return redisTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisSpringAppApplication.class, args);
	}


}
