UNIT TEST FOR SERVICE

package com.kgisl.docket.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kgisl.docket.PortfolioBuilder;
import com.kgisl.docket.Repository.PortfolioRepository;

import com.kgisl.docket.Service.PortfolioServiceImp;
import com.kgisl.docket.model.Portfolio;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioServiceUTest {
    // @InjectMocks
    // private PortfolioController portfolioController;
    @InjectMocks
    private PortfolioServiceImp portfolioService;

    @Mock
    private PortfolioRepository portfolioRepository;

    public static List<Portfolio> expectedPortfolio;
    private Portfolio portfolio11 = new PortfolioBuilder().id(1L).cat("cat").symbol("symbol").build();
    private Portfolio portfolio12 = new PortfolioBuilder().id(1L).cat("cats").symbol("symbol").build();

    @Test
    public void GetAllPortfolioTestService() {

        expectedPortfolio = Arrays.asList(portfolio11, portfolio12);
        System.out.println(
                "This returns a mockito stubbing" + when(portfolioRepository.findAll()).thenReturn(expectedPortfolio));
        List<Portfolio> actualPortfolio = portfolioService.getAll();
        System.out.println("*******" + expectedPortfolio + "******" + actualPortfolio);
        assertNotNull(actualPortfolio);
        assertEquals(expectedPortfolio, actualPortfolio);
    }

  
    @Test
    public void deletePortfolioServiceTest() {

        Long pId = 2L;

        portfolioService.delete(pId);
        verify(portfolioRepository).deleteById(pId);
    }

    @Test
    public void updatePortfolioServiceTest() {

        Long id = 1L;

        when(portfolioRepository.saveAndFlush(portfolio12)).thenReturn(portfolio12);
        Portfolio portfolio = portfolioService.update(portfolio12, id);
        System.out.println("PPPPPPPPPPoooooooooo" + portfolio);
        assertNotNull(portfolio);
        assertEquals(portfolio, portfolio12);
    }
  

    @Test
    public void savePortfolioServiceTest2() {
        List<Portfolio> port = new ArrayList<Portfolio>();
        port.add(portfolio12);
       // when(portfolioRepository.saveAndFlush(portfolio12)).thenReturn(portfolio12);
        when(portfolioRepository.findAll()).thenReturn(port);
    List<Portfolio> portfolios = new ArrayList<Portfolio>();
   

    portfolios.add(portfolio11);
    System.out.println("portfolios"+portfolios);
            List<Portfolio> portfoliosaveresult = portfolioService.save(portfolios);
            System.out.println("&&&&&&&&&&&&&" + portfoliosaveresult + "*************" + portfolioService.save(portfolios) );
            assertNotNull(portfoliosaveresult);
        
    }

}
