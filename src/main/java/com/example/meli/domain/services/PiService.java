package com.example.meli.domain.services;

import com.example.meli.commons.utils.SerialPi;
import com.example.meli.commons.utils.UtilFunction;
import com.example.meli.domain.models.NumberPi;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

@Service
public class PiService implements IPiService{

    public NumberPi getPiRandom(int randomNumber) {
        int random = UtilFunction.calculatedRandom(randomNumber);
        NumberPi number= new NumberPi(randomNumber,random,calculatedPi(random));
        return number;
    }
    @Cacheable(cacheNames = "number_pi", key = "#numberUSer" ,unless = "#result == null")
    public NumberPi getPiNotRandom(int numberUSer) {
        System.out.println("No estaba creado " + numberUSer + " se creará ");
        NumberPi number= new NumberPi(numberUSer,numberUSer,calculatedPi(numberUSer));
        return number;
    }


    public String calculatedPi(int decimales){
        SerialPi serie = new SerialPi(decimales);
        int incremento = 0;
        BigDecimal sumatoria =  new BigDecimal(0), anteriorSumatoria = new BigDecimal(0);
        boolean variable = true;
        while (variable){
            anteriorSumatoria = sumatoria;
            sumatoria = sumatoria.add(serie.calculateIteration(incremento));
            incremento +=1;
            variable = sumatoria.setScale(decimales, RoundingMode.FLOOR).equals(anteriorSumatoria.setScale(decimales, RoundingMode.FLOOR))
                    ? false
                    : true;

        }
        return sumatoria.setScale(decimales, RoundingMode.FLOOR).toString();

    }

}
