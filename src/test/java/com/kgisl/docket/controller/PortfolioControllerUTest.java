// USING REAL OBJECTS

package com.kgisl.docket.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kgisl.docket.PortfolioBuilder;
import com.kgisl.docket.Controller.PortfolioController;
import com.kgisl.docket.Repository.PortfolioRepository;
import com.kgisl.docket.Service.PortfolioService;
import com.kgisl.docket.model.Portfolio;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PortfolioControllerUTest {
        @InjectMocks
        private PortfolioController portfolioController;
 
        @Mock
        private PortfolioService portfolioService;
@Mock
private PortfolioRepository portfolioRepository;

        public static List<Portfolio>  expectedPortfolio;
        private Portfolio portfolio11=new PortfolioBuilder().id(1L).cat("cat").symbol("symbol").build();
        private Portfolio portfolio12=new PortfolioBuilder().id(1L).cat("cats").symbol("symbol").build();

        @Test
        public void findPortfolioTest() {

        expectedPortfolio=Arrays.asList(portfolio11,portfolio12);
        when(portfolioService.getAll()).thenReturn(expectedPortfolio);

        ResponseEntity<List<Portfolio>> actualCountries = portfolioController.all();

        assertNotNull(actualCountries);
        assertEquals(HttpStatus.OK, actualCountries.getStatusCode());
    }
 

    @Test
    public void addPortfolioTest() {

        List<Portfolio> port=new ArrayList<Portfolio>();
        port.add(portfolio12);
        when(portfolioService.save(port )).thenReturn(port);

        ResponseEntity<List<Portfolio>> AR = portfolioController.post(port);

        assertNotNull(AR);
    }

    @Test
    public void deletePortfolioTest() {
        Long id = 2L;
        portfolioRepository.deleteById(id);
        verify(portfolioRepository).deleteById(id);

    }
}

























