package com.example.meli;

import com.example.meli.commons.dto.DtoResponseDelete;
import com.example.meli.commons.exception.DeleteException;
import com.example.meli.commons.exception.RedisExceptionCustom;

import com.example.meli.commons.validator.RedisValidImp;
import com.example.meli.domain.models.BasePi;
import com.example.meli.domain.services.PiService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private PiService servicio;

    @MockBean
    private RedisValidImp validatorRedis;


    @BeforeAll
    public void setup() {

        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    }

    @Test
    public void getPiRandomControllerTest() throws Exception {
        BasePi numero= new BasePi(80,"3.14");
        Mockito.when(servicio.getPiRandom(80)).thenReturn(numero);
        this.mvc.perform(get("/getpi_random?input_number=80")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getPiRandomControllerTestFailMinor1() throws Exception {
        this.mvc.perform(get("/getpi_random?input_number=-1")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void getPiNoRandomControllerTest() throws Exception {
        BasePi numero= new BasePi(4,"3.14");
        Mockito.when(servicio.getPiNotRandom(4)).thenReturn(numero);
        this.mvc.perform(get("/getpi?random_number=4")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void evictPiTest() throws Exception {
        DtoResponseDelete response = new DtoResponseDelete("Borrado",24);
        Mockito.when(servicio.deletePi(24)).thenReturn(response);
        this.mvc.perform(delete("/deletePi?random_number=24")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void evictPiTestFailRedis() throws Exception {
        doThrow(new RedisExceptionCustom("","","")).when(validatorRedis).validator();
        DtoResponseDelete response = new DtoResponseDelete("Borrado",24);
        Mockito.when(servicio.deletePi(24)).thenReturn(response);
        this.mvc.perform(delete("/deletePi?random_number=24")).andDo(print()).andExpect(status().isConflict());
    }

    @Test
    public void evictPiTestFailDelete() throws Exception {
        doThrow(new DeleteException("","","")).when(servicio).deletePi(24);
        this.mvc.perform(delete("/deletePi?random_number=24")).andDo(print()).andExpect(status().isConflict());
    }


}
