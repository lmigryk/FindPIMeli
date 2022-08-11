package com.example.meli.commons.validator;

import com.example.meli.commons.exception.ApiExceptionBase;
import com.example.meli.commons.exception.RandomException;
import com.example.meli.config.AppEnv;
import org.springframework.stereotype.Component;

@Component
public class RandomValidImp implements BasicValid {

    private AppEnv appEnv;
    public RandomValidImp(AppEnv appEnv) {
        this.appEnv = appEnv;
    }

    @Override
    public void validator(Integer random) throws ApiExceptionBase {
        if ( random >= appEnv.getMaxRandomPrecision()) {
            throw new RandomException("Random parameter would cause overflow","CONFLICT_RANDOM_NOT_VALID","https://httpstatuses.com/409", random);
        }

    }
}
