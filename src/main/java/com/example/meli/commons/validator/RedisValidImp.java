package com.example.meli.commons.validator;

import com.example.meli.commons.exception.ApiExceptionBase;
import com.example.meli.commons.exception.RedisExceptionCustom;
import com.example.meli.commons.constants.ExceptionEnum;
import com.example.meli.config.AppEnv;
import org.springframework.stereotype.Component;

@Component
public class RedisValidImp {
    private AppEnv appEnv;
    public RedisValidImp(AppEnv appEnv) {
        this.appEnv = appEnv;
    }

    public void validator() throws ApiExceptionBase {
        if ( appEnv.getRedisEnabled() == 0) {
            throw new RedisExceptionCustom(ExceptionEnum.REDIS_INFO.getData(),
                    ExceptionEnum.REDIS_MESSAGE.getData(),
                    ExceptionEnum.URL.getData());
        }
    }
}
