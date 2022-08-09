package com.example.meli.domain.services;

import com.example.meli.commons.exception.ApiExceptionBase;
import com.example.meli.commons.exception.DeleteException;
import com.example.meli.commons.utils.SerialPi;
import com.example.meli.commons.validator.RandomConstraint;
import com.example.meli.domain.models.BasePi;
import lombok.SneakyThrows;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;
@Validated
@Service
public class PiService implements IPiService{
    private final CacheManager cacheManager;
    public PiService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    @Override
    @Cacheable(cacheNames = "number_pi", key = "#random" ,unless = "#result == null")
    public BasePi getPiRandom(int random, int paramNumber) {
        System.out.println("No estaba creado " + random + " se creará ");
        BasePi numberPi =  calculatedPi(random);
        numberPi.setParam(paramNumber);
        return numberPi;
    }
    @Override
    @Cacheable(cacheNames = "number_pi", key = "#numberUSer" ,unless = "#result == null")
    public BasePi getPiNotRandom(int numberUSer) {
        System.out.println("No estaba creado " + numberUSer + " se creará ");
        return calculatedPi(numberUSer);
    }

    @Override
    @SneakyThrows
    public void deletePi(int numberUSer)  {
        if (cacheManager.getCache("number_pi").get(numberUSer) != null) {
            System.out.println("si vez este mensaje es por q se elimino  " + numberUSer);
            cacheManager.getCache("number_pi").evict(numberUSer);
        }
        else{
            throw new DeleteException("Not found number key in cache redis","DONT_DELETE","https://httpstatuses.com/409");
        }

    }
    public BasePi calculatedPi( int decimales){
        SerialPi serie = new SerialPi(decimales);
        int incremento = 0;
        BigDecimal sumatoria =  new BigDecimal(0), anteriorSumatoria;
        boolean variable = true;
        while (variable){
            anteriorSumatoria = sumatoria;
            sumatoria = sumatoria.add(serie.calculateIteration(incremento));
            incremento +=1;
            variable = !sumatoria.setScale(decimales, RoundingMode.FLOOR).equals(anteriorSumatoria.setScale(decimales, RoundingMode.FLOOR));
        }
        BasePi result = new BasePi(decimales,sumatoria.setScale(decimales, RoundingMode.FLOOR).toString());

        return result;

    }

}
