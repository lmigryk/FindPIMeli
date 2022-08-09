package com.example.meli.commons.validator;

import com.example.meli.commons.exception.RandomException;
import com.example.meli.config.AppEnv;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RandomValidatorDecorator implements ConstraintValidator<RandomConstraint, Integer> {

    @Autowired
    private AppEnv appEnv;

    @Override
    public void initialize(RandomConstraint numberRandom) {
    }

    @Override
    public boolean isValid(Integer numberRandom,
                           ConstraintValidatorContext cxt) {

        if ( numberRandom >= appEnv.getMaxRandomPrecision()) {
            throw new RandomException("Random parameter would cause overflow","CONFLICT_RANDOM_NOT_VALID","https://httpstatuses.com/409", numberRandom);
        }
        return true;
    }
}
