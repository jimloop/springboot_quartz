package com.example.quartz.redisInterface;

public interface ICacheProvider {
    void setString(String key,String value);
    String getString(String key);
}
