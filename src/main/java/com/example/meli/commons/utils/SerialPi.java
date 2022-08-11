package com.example.meli.commons.utils;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;


@AllArgsConstructor
public class SerialPi {
    private int precision;

    public BigDecimal calculateIteration(int incremento){
        BigDecimal primerNumerador = calculateNumeradorValue(4, incremento, 1);
        BigDecimal segundoNumerador = calculateNumeradorValue(2, incremento, 4);
        BigDecimal tercerNumerador = calculateNumeradorValue(1, incremento, 5);
        BigDecimal cuartoNumerador = calculateNumeradorValue(1, incremento, 6);
        BigDecimal sumaNumeradores = sumNumeradores(primerNumerador,segundoNumerador,tercerNumerador,cuartoNumerador);
        BigDecimal denominadorFormula = new BigDecimal("16").pow(incremento);
        return sumaNumeradores.divide(denominadorFormula);

    }

    private BigDecimal calculateNumeradorValue(int numerador, int incrementador, int numSuma) {
        int denominador = (8 * incrementador + numSuma);
        BigDecimal nume = new BigDecimal(numerador);
        BigDecimal res = nume.divide(new BigDecimal(denominador), new MathContext(this.precision));
        return res;
    }
    private  BigDecimal sumNumeradores(BigDecimal n1, BigDecimal n2, BigDecimal n3, BigDecimal n4) {
        BigDecimal res = n2.add(n3);
        res= res.add(n4);
        res= n1.subtract(res);
        return res;
    }

}
