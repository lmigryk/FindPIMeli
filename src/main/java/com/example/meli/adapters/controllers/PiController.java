package com.example.meli.adapters.controllers;

import com.example.meli.commons.utils.UtilFunction;
import com.example.meli.commons.validator.RandomValidImp;
import com.example.meli.commons.validator.RedisValidImp;
import com.example.meli.domain.models.BasePi;
import com.example.meli.domain.services.IPiService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
@Slf4j
@RestController
public class PiController {
    private final IPiService servicio;
    private final RedisValidImp validatorRedis;
    private final UtilFunction util;

    private final RandomValidImp randomValidImp;

    public PiController(IPiService servicio, RedisValidImp validatorRedis, UtilFunction util, RandomValidImp randomValidImp) {
        this.servicio = servicio;
        this.validatorRedis = validatorRedis;
        this.util = util;
        this.randomValidImp = randomValidImp;
    }

    @GetMapping(value = "getpi_random")
    public ResponseEntity<?> getPiRandom(
           @Valid @RequestParam (value ="input_number") @Min(1) Integer input_number) {
        this.validatorRedis.validator();
        int random = util.calculatedRandom(input_number);
        this.randomValidImp.validator(random);
        BasePi n= servicio.getPiRandom(random);
        n.setParam(input_number);
        return new ResponseEntity<>(n, HttpStatus.CREATED);

    }

    @GetMapping(value = "getpi")
    public ResponseEntity<?> getPiNotRandom(
            @RequestParam(value="random_number") @Min(1) Integer random_number) {
        this.validatorRedis.validator();
        BasePi n= servicio.getPiNotRandom(random_number);
        n.setParam(null);
        return new ResponseEntity<>(n, HttpStatus.CREATED);

    }

    @DeleteMapping (value = "deletePi")
    public ResponseEntity<?> evictPiCache(
            @RequestParam(value="random_number") @Min(1) Integer random_number) {
        this.validatorRedis.validator();
        servicio.deletePi(random_number);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
