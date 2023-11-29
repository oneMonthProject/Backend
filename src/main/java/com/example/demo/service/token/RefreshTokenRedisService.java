package com.example.demo.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RefreshTokenRedisService {

    private static final String KEY_PREFIX = "refreshToken:";

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.refresh.token.expiration.millis}")
    private long refreshTokenExpiresMillis;

}
