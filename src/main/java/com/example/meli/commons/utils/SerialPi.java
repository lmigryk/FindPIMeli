package com.example.meli.commons.utils;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;


@AllArgsConstructor
public class SerialPi {
    private int precision;

    public BigDecimal calculateIteration(int increment){
        BigDecimal primerNumerador = calculateNumeradorValue(4, increment, 1);
        BigDecimal segundoNumerador = calculateNumeradorValue(2, increment, 4);
        BigDecimal tercerNumerador = calculateNumeradorValue(1, increment, 5);
        BigDecimal cuartoNumerador = calculateNumeradorValue(1, increment, 6);
        BigDecimal sumaNumeradores = sumNumeradores(primerNumerador,segundoNumerador,tercerNumerador,cuartoNumerador);
        BigDecimal denominadorFormula = new BigDecimal("16").pow(increment);
        return sumaNumeradores.divide(denominadorFormula);

    }

    private BigDecimal calculateNumeradorValue(int numerator, int incremental, int numSuma) {
        int denominador = (8 * incremental + numSuma);
        BigDecimal num = new BigDecimal(numerator);
        BigDecimal res = num.divide(new BigDecimal(denominador), new MathContext(this.precision));
        return res;
    }
    private  BigDecimal sumNumeradores(BigDecimal n1, BigDecimal n2, BigDecimal n3, BigDecimal n4) {
        BigDecimal res = n2.add(n3);
        res= res.add(n4);
        res= n1.subtract(res);
        return res;
    }

}
