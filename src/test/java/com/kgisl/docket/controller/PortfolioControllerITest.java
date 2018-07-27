package com.kgisl.docket.controller;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.kgisl.docket.DocketApplication;
import com.kgisl.docket.PortfolioBuilder;
import com.kgisl.docket.Repository.PortfolioRepository;
import com.kgisl.docket.model.Portfolio;
import static com.jayway.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static com.jayway.restassured.RestAssured.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = DocketApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class PortfolioControllerITest {
    private String port1="api/portfolio/get";
    private String port2="api/portfolio/post";
    private String port3="api/portfolio/delete/{pId}";
    private Portfolio portfolio11=new PortfolioBuilder().id(1L).cat("cat").symbol("symbol").build();
    private Portfolio portfolio12=new PortfolioBuilder().id(1L).cat("cats").symbol("symbol").build();

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Value("${local.server.port}")
    private int serverPort;
    private Portfolio portfolio;

    @Before
    public void setUp() {
        portfolioRepository.deleteAll();
        portfolio = portfolioRepository.save(portfolio11);
        RestAssured.port = serverPort;
    }
  @Test
    public void addItemShouldReturnSavedItem() {
        given().body(portfolio12).contentType(ContentType.JSON).when().post(port2).then();
    }


    @Test
    public void getItemsShouldReturnBothItems() {
        when().get(port1).then();
    }

    @Test
    public void deleteItemShouldReturnNoContent() {
        when().delete(port3, portfolio.getId()).then();
    }

}