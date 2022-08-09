package com.example.meli.commons.validator;

import com.example.meli.commons.exception.ApiExceptionBase;
import com.example.meli.commons.exception.RandomException;
import com.example.meli.commons.exception.RedisException;
import com.example.meli.config.AppEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisValidImp {
    @Autowired
    private AppEnv appEnv;
    public void validator() throws ApiExceptionBase {
        if ( appEnv.getRedisEnabled() == 0) {
            throw new RedisException("Redis Server Disabled","CONFLICT_REDIS_DISABLED","https://httpstatuses.com/409");
        }
    }
}
