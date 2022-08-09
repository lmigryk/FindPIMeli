package com.example.meli.adapters.controllers;

import com.example.meli.commons.utils.UtilFunction;
import com.example.meli.commons.validator.RandomConstraint;
import com.example.meli.commons.validator.RandomValidImp;
import com.example.meli.commons.validator.RedisValidImp;
import com.example.meli.domain.models.BasePi;
import com.example.meli.domain.services.IPiService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
@Slf4j
@RestController
public class PiController {
    private final IPiService servicio;
    private final RandomValidImp validatorRandom;
    private final RedisValidImp validatorRedis;

    public PiController(IPiService servicio,  RandomValidImp validatorRandom, RedisValidImp validatorRedis) {
        this.servicio = servicio;
        this.validatorRandom = validatorRandom;
        this.validatorRedis = validatorRedis;
    }

    @GetMapping(value = "getpi_random")
    public ResponseEntity getPiRandom(
           @Valid @RequestParam (value ="input_number") @Min(1) Integer input_number) {
        int random = UtilFunction.calculatedRandom(input_number);
        this.validatorRedis.validator();
        this.validatorRandom.validator(random);
        BasePi n= servicio.getPiRandom(random, input_number);
        n.setParam(input_number);
        return new ResponseEntity<>(n, HttpStatus.CREATED);

    }

    @GetMapping(value = "getpi")
    public ResponseEntity getPiNotRandom(
            @RequestParam(value="random_number") @RandomConstraint @Min(1) Integer random_number) {
        this.validatorRedis.validator();
        BasePi n= servicio.getPiNotRandom(random_number);
        n.setParam(null);
        return new ResponseEntity<>(n, HttpStatus.CREATED);

    }

    @DeleteMapping (value = "deletePi")
    public ResponseEntity evictPiCache(
            @RequestParam(value="random_number") @Min(1) Integer random_number) {
        this.validatorRedis.validator();
        servicio.deletePi(random_number);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
