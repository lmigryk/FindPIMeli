package com.example.meli.adapters.controllers;

import com.example.meli.commons.utils.UtilFunction;
import com.example.meli.commons.validator.RandomValidImp;
import com.example.meli.commons.validator.RedisValidImp;
import com.example.meli.domain.models.BasePi;
import com.example.meli.domain.services.PiService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
@RestController
public class PiController {
    private final  PiService servicio;
    private final RedisValidImp validatorRedis;
    private final UtilFunction util;
    private final RandomValidImp randomValidImp;

    public PiController(PiService service, RedisValidImp validatorRedis, UtilFunction util, RandomValidImp randomValidImp) {
        this.servicio = service;
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
        return new ResponseEntity<>(n, HttpStatus.OK);

    }

    @GetMapping(value = "getpi")
    public ResponseEntity<?> getPiNotRandom(
            @RequestParam(value="random_number") @Min(1) Integer random_number) {
        this.validatorRedis.validator();
        BasePi n= servicio.getPiNotRandom(random_number);
        n.setParam(null);
        return new ResponseEntity<>(n, HttpStatus.OK);

    }

    @DeleteMapping (value = "deletePi")
    public ResponseEntity<?> evictPiCache(
            @RequestParam(value="random_number") @Min(1) Integer random_number) {
        this.validatorRedis.validator();
        return new ResponseEntity<>(servicio.deletePi(random_number), HttpStatus.OK);

    }
}
