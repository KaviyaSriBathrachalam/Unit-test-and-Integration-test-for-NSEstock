#INTEGRATION TEST MOCK TEST FOR CONTROLLER

package com.kgisl.docket.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Arrays;
import java.util.List;

import com.kgisl.docket.PortfolioBuilder;
import com.kgisl.docket.Controller.PortfolioController;
import com.kgisl.docket.Service.PortfolioService;
import com.kgisl.docket.model.Portfolio;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PortfolioController.class, secure = false)
public class PortfolioControllerMVCtest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PortfolioService portfolioService;

    public static List<Portfolio> expectedPortfolio;
    public static List<Portfolio> actualPortfolio;
    private Portfolio portfolio11 = new PortfolioBuilder().id(1L).cat("cat").symbol("symbol").build();
    private Portfolio portfolio12 = new PortfolioBuilder().id(1L).cat("cat").symbol("symbol").build();

    @Test
    public void testAll() throws Exception {
        
        expectedPortfolio = Arrays.asList(portfolio11);
        actualPortfolio = Arrays.asList( portfolio12);
        System.out.println(portfolio11 + "%%%%%%%%%%%%%" + expectedPortfolio);

        given(portfolioService.getAll()).willReturn(expectedPortfolio);
        mockMvc.perform(get("/api/portfolio/get").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'cat':'cat','symbol':'symbol'}]"));

    }
    



}
