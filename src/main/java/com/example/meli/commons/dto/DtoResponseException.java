package com.example.meli.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DtoResponseException {
    @Getter
    @Setter
    private  String userMessage;
    @Getter
    @Setter
    private  String internalMessage;
    @Getter
    @Setter
    private  String userMoreInfo;

    @Getter
    @Setter
    private  Integer randomGenerate;

    public DtoResponseException(String userMessage, String internalMessage, String userMoreInfo) {
        this.userMessage = userMessage;
        this.internalMessage = internalMessage;
        this.userMoreInfo = userMoreInfo;
        this.randomGenerate = null;
    }

    public DtoResponseException(String userMessage, String internalMessage, String userMoreInfo, Integer randomGenerate) {
        this.userMessage = userMessage;
        this.internalMessage = internalMessage;
        this.userMoreInfo = userMoreInfo;
        this.randomGenerate = randomGenerate;
    }
}
