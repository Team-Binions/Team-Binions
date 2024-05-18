package com.beanions.user.service;

import com.beanions.auth.dao.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling
public class VisitorService {

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;
    @Autowired
    private UserMapper userMapper;
    private static final String VISITOR_COUNT_KEY = "visitorCount";
    private static final String USER_KEY_PREFIX = "user:";


    public void incrementVisitorCount(HttpServletRequest request) {
        String userIp = getClientIp(request);
        System.out.println("=======================================================");
        System.out.println("접근한 ClientIP : " + userIp);
        String userKey = USER_KEY_PREFIX + userIp;
//        System.out.println(redisTemplate);

        // Check if the user has already incremented the counter
        if (Boolean.TRUE.equals(redisTemplate.hasKey(userKey))) {
            System.out.println("이미 접근한 IP --- 본 IP로 접근한 캐싱은 1시간 후에 적용됩니다..");
            return;
        }

        // Increment visitor count and set user key with TTL
        redisTemplate.opsForValue().increment(VISITOR_COUNT_KEY, 1);
        redisTemplate.opsForValue().set(userKey, 1);
        redisTemplate.expire(userKey, 1, TimeUnit.HOURS); // Set TTL for 1 hour
    }

    public Integer getVisitorCount() {
        Integer count = redisTemplate.opsForValue().get(VISITOR_COUNT_KEY);
        return count != null ? count : 0;
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || remoteAddr.isEmpty()) {
            remoteAddr = request.getRemoteAddr();
        }
        return remoteAddr;
    }

    int count;
    @Scheduled(fixedRate = 1800000) // 30분마다 실행
    public void updateVisitorCountInDatabase() {
        count++;
        Integer visitorCount = redisTemplate.opsForValue().get(VISITOR_COUNT_KEY);
        if (visitorCount != null && visitorCount > 0) {
            userMapper.insertVisitorCount(visitorCount);
            System.out.println(count + "번째 AddVisitorCount : " + visitorCount);
            redisTemplate.delete(VISITOR_COUNT_KEY);
            System.out.println("캐시 삭제 완료!");
        }
    }
}
