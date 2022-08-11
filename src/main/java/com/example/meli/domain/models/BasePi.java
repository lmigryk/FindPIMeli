package com.example.meli.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Modelo Pi", description = "Numero el cual retornará post petición")

public class BasePi implements Serializable{
    public BasePi(int random, String piCalc) {
        this.random = random;
        this.piCalc = piCalc;
        this.param = null;
    }

    @ApiModelProperty(value = "Param")
    @Getter
    @Setter
    private Integer param ;
    @ApiModelProperty(value = "Random")
    @Getter
    @Setter
    private Integer random;
    @ApiModelProperty(value = "Pi Calculado")

    @Getter
    @Setter
    private String piCalc;
}
