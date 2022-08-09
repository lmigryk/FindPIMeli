package com.example.meli.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)

public class BasePi implements Serializable{
    public BasePi(int random, String piCalc) {
        this.random = random;
        this.piCalc = piCalc;
        this.param = null;
    }

    @Getter
    @Setter
    private Integer param ;

    @Getter
    @Setter
    private Integer random;

    @Getter
    @Setter
    private String piCalc;
}
