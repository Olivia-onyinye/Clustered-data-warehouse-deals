package com.progressSoft.Data.Warehouse.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
import com.progressSoft.Data.Warehouse.dtos.DealResponse;
import com.progressSoft.Data.Warehouse.services.DealService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.math.BigDecimal;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DealService dealService;

    @Test
    void saveDealRequests_ReturnsCreated() throws Exception {
        DealRequestDto dealRequestDto = new DealRequestDto("DR001", "USD", "ngn", new BigDecimal("2000.00"));
        String jsonRequest = objectMapper.writeValueAsString(Collections.singletonList(dealRequestDto));
        Mockito.doNothing().when(dealService).saveDealRequests(Collections.singletonList(dealRequestDto));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/deals/requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void getDeal_byUniqueId_ReturnsOk() throws Exception {
        String dealUniqueId = "DR001";
        DealResponse response = new DealResponse("DR001", "USD", "EUR", BigDecimal.TEN);
        when(dealService.getByUniqueId(dealUniqueId)).thenReturn(response);

        mockMvc.perform(get("/api/deals/" + dealUniqueId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
}
}