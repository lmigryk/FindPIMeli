package com.example.meli.domain.services;

import com.example.meli.commons.dto.DtoResponseDelete;
import com.example.meli.commons.exception.DeleteException;
import com.example.meli.commons.utils.SerialPi;
import com.example.meli.domain.models.BasePi;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Validated
@Service
public class PiServiceImp implements PiService {

    private final CacheManager cacheManager ;
    private final DtoResponseDelete response = new DtoResponseDelete();

    public PiServiceImp(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    @Override
    @Cacheable(cacheNames = "number_pi", key = "#random" ,unless = "#result == null")
    public BasePi getPiRandom(int random) {
        System.out.println("No estaba creado " + random + " se creará ");
        return calculatedPi(random);
    }
    @Override
    @Cacheable(cacheNames = "number_pi", key = "#numberUSer" ,unless = "#result == null")
    public BasePi getPiNotRandom(int numberUSer) {
        System.out.println("No estaba creado " + numberUSer + " se creará ");
        return calculatedPi(numberUSer);
    }

/*    @Override
    @SneakyThrows
    public DtoResponseDelete deletePi2(int numberUSer)  {
        System.out.println("hola");
        if (Objects.requireNonNull(cacheManager.getCache("number_pi")).get(numberUSer) != null) {
            System.out.println("si vez este mensaje es por q se elimino  " + numberUSer);
            Objects.requireNonNull(cacheManager.getCache("number_pi")).evict(numberUSer);
            response.setMessage("Num delete correctly");
            response.setNumber(numberUSer);
            return response;
        }
        else{
            throw new DeleteException("Not found number key in cache redis","DONT_DELETE","https://httpstatuses.com/409");
        }

    }*/

    @Override
    @CacheEvict(cacheNames = "number_pi", key = "#numberUSer")
    public DtoResponseDelete deletePi(int numberUSer)  {
        System.out.println("hola");
        Cache cache = cacheManager.getCache("number_pi");
        System.out.println("hola");
        if (cache.get(numberUSer) != null) {
            System.out.println("si vez este mensaje es por q se elimino  " + numberUSer);
            response.setMessage("Num delete correctly");
            response.setNumber(numberUSer);
            return response;
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
