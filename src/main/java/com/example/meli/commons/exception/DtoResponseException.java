package com.example.meli.commons.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DtoResponseException {
    @Getter
    @Setter
    private final String userMessage;
    @Getter
    @Setter
    private final String internalMessage;
    @Getter
    @Setter
    private final String userMoreInfo;

    @Getter
    @Setter
    private final String randomGenerate;

    public DtoResponseException(String userMessage, String internalMessage, String userMoreInfo) {
        this.userMessage = userMessage;
        this.internalMessage = internalMessage;
        this.userMoreInfo = userMoreInfo;
        this.randomGenerate = null;
    }

    public DtoResponseException(String userMessage, String internalMessage, String userMoreInfo, String randomGenerate) {
        this.userMessage = userMessage;
        this.internalMessage = internalMessage;
        this.userMoreInfo = userMoreInfo;
        this.randomGenerate = randomGenerate;
    }
}
