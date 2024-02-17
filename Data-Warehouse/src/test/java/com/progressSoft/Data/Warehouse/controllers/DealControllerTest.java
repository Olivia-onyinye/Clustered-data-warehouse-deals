//package com.progressSoft.Data.Warehouse.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
//import com.progressSoft.Data.Warehouse.enums.CurrencyIsoCode;
//import com.progressSoft.Data.Warehouse.services.DealService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//class DealControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private DealService dealService;
//
//    @Test
//    void saveDealRequests_ReturnsCreated() throws Exception {
//        DealRequestDto dealRequestDto = new DealRequestDto("DR001", USD, NGN, new BigDecimal("1000.00"));
//        String jsonRequest = objectMapper.writeValueAsString(Collections.singletonList(dealRequestDto));
//        Mockito.doNothing().when(dealService).saveDealRequests(Collections.singletonList(dealRequestDto));
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/deals/requests")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isCreated())
//                .andReturn();
//    }
//}