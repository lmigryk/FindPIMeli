package com.example.meli.adapters.controllers;

import com.example.meli.commons.validator.RedisValidImp;
import com.example.meli.domain.models.BasePi;
import com.example.meli.domain.services.PiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Numero Pi controller", produces = "application/json")

public class PiController {
    private final PiService servicio;
    private final RedisValidImp validatorRedis;

    public PiController(PiService service, RedisValidImp validatorRedis) {
        this.servicio = service;
        this.validatorRedis = validatorRedis;
    }

    @ApiOperation(value = "Conseguir decimales de Pi a traves de un número random", notes = "A partir de un " +
            "número ingresado por el usuario, se calcula un numero random y se muestran los decimales de PI ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Obtención de número pi exitoso")
    })
    @GetMapping(value = "getpi_random")
    public ResponseEntity<?> getPiRandom(
            @Valid @RequestParam(value = "input_number") @Min(1) Integer input_number) {
        this.validatorRedis.validator();
        BasePi n = servicio.getPiRandom(input_number);
        n.setParam(input_number);
        return new ResponseEntity<>(n, HttpStatus.OK);


    }
    @ApiOperation(value = "Conseguir decimales de Pi a traves de un número ingresado", notes = "A partir de un número ingresado, se calculas los decimales de PI")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Obtención de número pi exitoso")
    })
    @GetMapping(value = "getpi")
    public ResponseEntity<?> getPiNotRandom(
            @RequestParam(value = "random_number") @Min(1) Integer random_number) {
        this.validatorRedis.validator();
        BasePi n = servicio.getPiNotRandom(random_number);
        n.setParam(null);
        return new ResponseEntity<>(n, HttpStatus.OK);


    }
    @ApiOperation(value = "Eliminar numero Pi de caché", notes = "Al ingresar un numero, este se va a buscar a la cache para ser eliminado ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Eliminación de registro exitoso")
    })
    @DeleteMapping(value = "deletePi")
    public ResponseEntity<?> evictPiCache(
            @RequestParam(value = "random_number") @Min(1) Integer random_number) {
        this.validatorRedis.validator();
        return new ResponseEntity<>(servicio.deletePi(random_number), HttpStatus.OK);
    }
}
