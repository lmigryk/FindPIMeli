package com.example.meli.adapters.controllers;

import com.example.meli.domain.services.IPiService;
import com.example.meli.domain.services.PiService;
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
            return new ResponseEntity<>(servicio.getPiRandom(input_number), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "getpi")
    public ResponseEntity getPiNotRandom(
            @RequestParam int random_number) {
        try {
            return new ResponseEntity<>(servicio.getPiNotRandom(random_number), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
