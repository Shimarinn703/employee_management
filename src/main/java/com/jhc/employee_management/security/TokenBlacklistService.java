package com.jhc.employee_management.security;

public interface TokenBlacklistService {
    void blacklist(String token, long expiresInMillis);
    boolean isBlacklisted(String token);
}