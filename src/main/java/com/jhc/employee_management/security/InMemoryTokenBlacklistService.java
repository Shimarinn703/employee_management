package com.jhc.employee_management.security;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryTokenBlacklistService implements TokenBlacklistService {
    private final Map<String, Long> map = new ConcurrentHashMap<>();

    @Override
    public void blacklist(String token, long expiresInMillis) {
        map.put(token, System.currentTimeMillis() + Math.max(1, expiresInMillis));
    }

    @Override
    public boolean isBlacklisted(String token) {
        Long deadline = map.get(token);
        if (deadline == null) return false;
        if (System.currentTimeMillis() > deadline) {
            map.remove(token);
            return false;
        }
        return true;
    }
}
