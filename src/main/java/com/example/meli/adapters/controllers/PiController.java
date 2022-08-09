package com.example.meli.adapters.controllers;

import com.example.meli.commons.utils.UtilFunction;
import com.example.meli.domain.models.BasePi;
import com.example.meli.domain.services.IPiService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
@Slf4j
@RestController
public class PiController {
    private final IPiService servicio;

    @Autowired
    public PiController(IPiService servicio) {
        this.servicio = servicio;
    }

    @GetMapping(value = "getpi_random")
    public ResponseEntity getPiRandom(
            @RequestParam int input_number) {
        try {
            int random = UtilFunction.calculatedRandom(input_number);
            BasePi n= servicio.getPiRandom(random, input_number);
            n.setParam(input_number);
            return new ResponseEntity<>(n, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "getpi")
    public ResponseEntity getPiNotRandom(
            @RequestParam int random_number) {
        try {
            BasePi n= servicio.getPiNotRandom(random_number);
            n.setParam(null);
            return new ResponseEntity<>(n, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping (value = "deletePi")
    public ResponseEntity evictPiCache(
            @RequestParam int random_number) {
            servicio.deletePi(random_number);
            return new ResponseEntity<>( HttpStatus.OK);
    }
}
